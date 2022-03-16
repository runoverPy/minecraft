package game.mechanics;

import game.core.server.core.Chunk;

public abstract class WorldGenerator {
    public abstract Chunk generateChunk(int x, int y, int z);
}
