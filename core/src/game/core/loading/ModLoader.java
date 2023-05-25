package game.core.loading;

import game.core.GameRuntime;
import mdk.Mod;
import mdk.blocks.BlockLoader;
import mdk.worldgen.WorldGeneratorLoader;

public class ModLoader implements mdk.ModLoader {
    private final Mod mod;
    private final GameRuntime runtime;

    public ModLoader(Mod mod, GameRuntime runtime) {
        this.mod = mod;
        this.runtime = runtime;
    }

    public void addDependency(String dependency) {

    }

    @Override
    public BlockLoader loadBlocks() {
        return runtime.getBlockRegister().getModBlockLoader(mod.getModName());
    }

    @Override
    public WorldGeneratorLoader loadGenerators() {
        return runtime.getGeneratorRegister();
    }
}

