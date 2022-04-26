package game.core.server;

import game.core.modding.WorldGenerator;

public class GeneratedWorld extends World {
    private final WorldGenerator generator;

    public GeneratedWorld(WorldGenerator generator) {
        this.generator = generator;
    }

    @Override
    protected Chunk loadChunk(int cX, int cY, int cZ) {
        return generator.generateChunk(cX, cY, cZ);
    }

    @Override
    protected void unloadChunk(int cX, int cY, int cZ, Chunk chunk) {

    }
}
