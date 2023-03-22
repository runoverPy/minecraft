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
     * @return The current unique modpack identifier
     */
    public String getSignature() {
        return "";
    }

    public void loadMods(Mod[] mods, GameRuntime runtime) {
        BlockRegister blockRegister = runtime.getBlockRegister();
        WorldGeneratorRegister generatorRegister = runtime.getGeneratorRegister();
        for (Mod mod : mods) {
            loadMod(mod, blockRegister, generatorRegister);
        }
    }

    private void loadMod(Mod mod, BlockRegister blockRegister, WorldGeneratorRegister generatorRegister) {
        String modName = mod.getModName();
        mod.defineBlocks(blockRegister);
        mod.defineGenerators(generatorRegister);
    }
}
