package game.core.server;

import game.mechanics.entities.Entity;
import org.joml.Vector3i;

public interface Client {
    void updateBlock(Vector3i block);
    void updateBlock(int x, int y, int z);

    void userUpdate(Entity.EntityUpdate update);

    void loadChunk(int cX, int cY, int cZ);
    void dropChunk(int cX, int cY, int cZ);
}