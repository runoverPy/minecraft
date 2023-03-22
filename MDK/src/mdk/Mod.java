package mdk;

import mdk.blocks.BlockLoader;
import mdk.worldgen.WorldGeneratorLoader;

public abstract class Mod {
    public abstract void loadMod(ModLoader loader);
    public abstract String getModName();
    public abstract void defineBlocks(BlockLoader loader);
    public abstract void defineGenerators(WorldGeneratorLoader register);
}
