package game.core.server;

import java.io.IOException;

public interface WorldSaver {
    Chunk loadChunk(int cX, int cY, int cZ);

    boolean isChunkSaved(int cX, int cY, int cZ);

    void saveChunk(int cX, int cY, int cZ, Chunk chunk) throws IOException;
}