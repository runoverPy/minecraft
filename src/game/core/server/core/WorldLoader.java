package game.core.server.core;

import org.joml.Vector3i;

import java.io.File;

/**
 *      worldName
 *       ├ world.json
 *       └ chunks
 *          └ chunk.dat
 */
public class WorldLoader {
    private long seed;

    private final File worldFile;

    public WorldLoader(String worldName) {
        this.worldFile = new File(getClass().getResource(worldName).toString());

    }

    public WorldLoader(String worldName, long seed) {
        this.worldFile = new File(getClass().getResource(worldName).toString());
    }

    public Chunk loadChunk(Vector3i chunk) {
        return null;
    }

    public long getSeed() {
        return seed;
    }
}