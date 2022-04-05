package game.core.modding;

import game.core.server.Chunk;

public abstract class WorldGenerator {
    public abstract Chunk generateChunk(int x, int y, int z);
}
