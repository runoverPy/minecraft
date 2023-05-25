package game.core.loading;

import game.core.GameRuntime;
import mdk.Mod;

import java.util.HashMap;
import java.util.Map;

public class ModHandler {
    private final Map<String, Mod> loadedMods;

    public ModHandler(Mod[] mods, GameRuntime runtime) {
        loadedMods = new HashMap<>();
        loadMods(mods, runtime);
    }

    /**
     * Creates a unique string identifier for the current modpack
     * (String modName, String modVersion)[] -> Byte[128]; such that the individual tuples are unordered
     * @return The current unique modpack identifier
     */
    public String getSignature() {
        return "";
    }

    public void loadMods(Mod[] mods, GameRuntime runtime) {
        for (Mod mod : mods) {
            mod.loadMod(new ModLoader(mod, runtime));
        }
    }
}
