package game.core.modding;

import game.core.server.Chunk;

public interface WorldGenerator {
    Chunk generateChunk(int x, int y, int z);
}
