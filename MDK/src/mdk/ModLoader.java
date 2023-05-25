package mdk;

import mdk.blocks.BlockLoader;
import mdk.worldgen.WorldGeneratorLoader;

public interface ModLoader {

    BlockLoader loadBlocks();

    WorldGeneratorLoader loadGenerators();
}
