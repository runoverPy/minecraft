package mdk.worldgen;

import mdk.blocks.BlockFactory;
import mdk.blocks.Chunk;

public interface WorldGenerator {
    void populateChunk(int cX, int cY, int cZ, Chunk<?> chunk, BlockFactory factory);
}