package game.core.modding.worldgen;

import game.core.server.Chunk;

public interface WorldGenerator {
    Chunk generateChunk(int cX, int cY, int cZ);
}