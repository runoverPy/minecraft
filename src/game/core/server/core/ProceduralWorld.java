package game.core.server.core;

public class ProceduralWorld extends World {
    private final long seed;
    // private final WorldLoader loader;
    // private final WorldBuilder builder;

    public ProceduralWorld(String worldName, long seed) {
        this.seed = seed;

    }

    public ProceduralWorld(String worldName) {
        this.seed = 0L;

    }

    @Override
    protected Chunk loadChunk(int cX, int cY, int cZ) {
        return null;
    }

    @Override
    protected void unloadChunk(int cX, int cY, int cZ, Chunk chunk) {

    }
}