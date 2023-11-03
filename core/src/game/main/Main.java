package game.main;

import game.assets.menus.ErrorScreen;
import game.assets.menus.MenuHandler;
import game.assets.text.ProportionalFont;
import game.core.GraphicsEngine;
import game.window.GLFWWindow;
import game.core.GameManager;
import game.core.GameRuntime;
import game.core.settings.GeneralSettings;
import game.window.WindowSizeCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.stb.STBIZlibCompress;

import java.io.*;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public class Main {
    private static final Path rootPath;

    static {
        try {
            rootPath = Path.of(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    private static Path logPath;

    public static Path getLogPath() {
        return logPath;
    }

    private static GraphicsEngine engine;
    private static GLFWWindow window;

    private static ProportionalFont font;
    private static GeneralSettings settings;
    private static GameRuntime runtime;
    private static GameManager game;
    private static MenuHandler menu;

    public static void main(String[] args) throws IOException {
        logPath = Paths.get("logs");
        if (!Files.isDirectory(logPath)) Files.createDirectory(logPath);
        Path
          outPath = logPath.resolve("stdout.txt"),
          errPath = logPath.resolve("stderr.txt");
        if (!Files.isRegularFile(outPath)) Files.createFile(outPath);
        if (!Files.isRegularFile(errPath)) Files.createFile(errPath);

        class BranchOutputStream extends OutputStream {
            private OutputStream[] recipients;

            public BranchOutputStream(OutputStream... recipients) {
                this.recipients = recipients;
            }

            @Override
            public void write(int b) throws IOException {
                for (OutputStream recipient : recipients)
                    recipient.write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                for (OutputStream recipient : recipients)
                    recipient.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                for (OutputStream recipient : recipients)
                    recipient.write(b, off, len);
            }

            @Override
            public void flush() throws IOException {
                for (OutputStream recipient : recipients)
                    recipient.flush();
            }
        }

        OutputStream
          logout = Files.newOutputStream(outPath, StandardOpenOption.TRUNCATE_EXISTING),
          stdout = new FileOutputStream(FileDescriptor.out),
          out = new BranchOutputStream(logout, stdout),
          logerr = Files.newOutputStream(errPath, StandardOpenOption.TRUNCATE_EXISTING),
          stderr = new FileOutputStream(FileDescriptor.err),
          err = new BranchOutputStream(logerr, stderr);

        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));

        setup();
        exec();
    }

    private static void setup() throws IOException {
        // init glfw
        if (!glfwInit())
            throw new RuntimeException("GLFW konnte nicht initialisiert werden");
        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
        glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, GLFW_TRUE);
//        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        // init window
        String title = "HELP IM STUCK IN MINECRAFT";
        boolean fullscreen = false;
        window = GLFWWindow.createOnPrimaryMonitor(title, fullscreen);
        window.setContext();
        window.setIcon("/img/MeinCraft.png");

        // init engine
        engine = new GraphicsEngine();
        engine.init();

        // init stuff
        runtime = GameRuntime.setInstance();
        menu = MenuHandler.mainMenu();
        font = new ProportionalFont();
        settings = new GeneralSettings();
    }

    private static void exec() {
        // rendering loop
        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            if (game != null) game.render(); // todo cleanup this
            else menu.render();
            window.update();
        }
        // cleanup
        window.destroy();
        if (game != null)
            game.closeGame();
        glfwTerminate();
    }

    public static GLFWWindow getActiveWindow() {
        return window;
    }

    public static void openGame(GameManager game) {
        if (Main.game == null) {
            Main.game = game;
            menu.disable();
            menu.cleanup();
            menu = null;
            System.gc();
        }
    }

    public static void closeGame() {
        if (game != null) {
            game.closeGame();
            game = null;
            menu = MenuHandler.mainMenu();
            System.gc();
        }
    }

    public static void crashGame(Throwable error) {
        if (game != null) {
            game.closeGame();
            game = null;
            setError(error);
        }
    }

    public static void setError(Throwable error) {
        if (menu == null) menu = MenuHandler.mainMenu(error);
        else menu.next(new ErrorScreen(menu, error));
    }

    /**
     * When a fatal error has occurred, and it is not useful to continue running the process
     * this method can be called to crash the program.
     */
    public static void crash(Throwable exception) {
        exception.printStackTrace(System.err);
        System.exit(0xf);
    }

    public static ProportionalFont getFont() {
        return font;
    }

    public static GeneralSettings getSettings() {
        return settings;
    }

    private static final long startTime = System.currentTimeMillis();
    public static long getStartTime() {
        return startTime;
    }
    public static long getCurrentTime() {
        return System.currentTimeMillis() - startTime;
    }
}
