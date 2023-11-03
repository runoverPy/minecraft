package game.core.loading;

import mdk.Mod;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 */
public class ModRegister {
    private final Map<String, RegisteredMod> registeredMods;

    public ModRegister() {
        registeredMods = new HashMap<>();
    }

    public void acquireMods(Path modDir) {
        if (!Files.exists(modDir)) return;
        try (Stream<Path> contents = Files.list(modDir)) {
            for (Path file : contents.toList()) {
                if (!Files.exists(file)) return;
                if (verifyModJar(file)) loadModJar(file);
            }
        } catch (IOException ioe) {
            System.err.println("Could not load Mods from " + modDir + " due to:");
            ioe.printStackTrace();
        }
    }

    private boolean verifyModJar(Path jarPath) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.jar");
        return matcher.matches(jarPath.getFileName());
    }

    private void loadModJar(Path jarPath) throws IOException {
        registeredMods.put(jarPath.getFileName().toString(), new RegisteredMod(jarPath));
    }

    public String[] getModList() {
        return registeredMods.keySet().toArray(String[]::new);
    }

    public Mod[] getActiveModHandles() {
        return registeredMods.values().stream().map(RegisteredMod::getModInstance).toArray(Mod[]::new);
    }

    private static class RegisteredMod {
        private static final ClassLoader parentClassLoader = Mod.class.getClassLoader();

        Path jarPath;
        ClassLoader modClassLoader;

        public RegisteredMod(Path jarPath) throws IOException {
            this.jarPath = jarPath;
            this.modClassLoader = new URLClassLoader(new URL[] {jarPath.toUri().toURL()}, parentClassLoader);
        }

        public Mod getModInstance() {
            // TODO: 21.03.23 there are many possible error sources in this code that should be reported cleanly

            try {
                InputStream jsonStream = modClassLoader.getResourceAsStream("mod.json");
                assert jsonStream != null;
                JSONObject modJson = new JSONObject(new JSONTokener(jsonStream));
                String modClassName = modJson.getString("mod-class");
                Class<?> modClass = modClassLoader.loadClass(modClassName);
                return (Mod) modClass.getDeclaredConstructor().newInstance();
            } catch (ReflectiveOperationException rfe) {
                throw new RuntimeException(rfe);
            }
        }
    }
}
