package game.core.server.core;

import org.joml.Vector3i;

public interface Client {
    void updateBlock(Vector3i block);
    void updateBlock(int x, int y, int z);
    void loadChunk(int cX, int cY, int cZ);
}
