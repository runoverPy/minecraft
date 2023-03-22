package game.core.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3i;

public interface Renderer {
    void updateBlock(Vector3i block);

    void updateChunks(Vector3i centerChunk, int renderRadius, boolean circular);

    void loadChunk(Vector3i chunk);

    void dropChunk(Vector3i chunk);

    void draw(Matrix4f matrixPV);

    MaterialDatabase getMaterialDatabase();
}
