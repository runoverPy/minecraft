package game.core.rendering;

import game.assets.BlockFrame;
import game.util.Side;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

public interface Renderer {
    /**
     * draws a frame around the specified chunk
     * @param matrixPV ProjectionView matrix with which the frame is to be rendered
     * @param chunkPos the position of the chunk
     */
    static void drawChunk(Matrix4f matrixPV, Vector3i chunkPos) {
        Vector3f chunkOffset = new Vector3f(chunkPos).mul(16);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                BlockFrame.draw(matrixPV, new Vector3f(i, j, 16).add(chunkOffset), Side.BOTTOM);
                BlockFrame.draw(matrixPV, new Vector3f(i, j, 0).add(chunkOffset), Side.BOTTOM);
                BlockFrame.draw(matrixPV, new Vector3f(i, 16, j).add(chunkOffset), Side.EAST);
                BlockFrame.draw(matrixPV, new Vector3f(i, 0, j).add(chunkOffset), Side.EAST);
                BlockFrame.draw(matrixPV, new Vector3f(16, i, j).add(chunkOffset), Side.SOUTH);
                BlockFrame.draw(matrixPV, new Vector3f(0, i, j).add(chunkOffset), Side.SOUTH);
            }
        }
    }

    void updateBlock(Vector3i block);

    void updateChunks(Vector3i centerChunk, int renderRadius, boolean circular);

    void loadChunk(Vector3i chunk);

    void dropChunk(Vector3i chunk);

    void draw(Matrix4f matrixPV);
}
