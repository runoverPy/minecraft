package game.core.rendering;

import game.assets.BlockFrame;
import game.core.GameRuntime;
import mdk.blocks.Material;
import game.util.Side;
import game.util.Util;

import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL43.*;

public class RenderUtils {
    private static final float[] combinedVertices = new float[] {
            1, 0, 1, 2f / 4, 2f / 3,
            1, 1, 1, 1f / 4, 2f / 3,
            0, 1, 1, 1f / 4, 1f / 3,
            0, 0, 1, 2f / 4, 1f / 3,

            0, 0, 0, 3f / 4, 2f / 3,
            0, 1, 0, 4f / 4, 2f / 3,
            1, 1, 0, 4f / 4, 1f / 3,
            1, 0, 0, 3f / 4, 1f / 3,

            0, 1, 1, 2f / 4, 1f / 3,
            1, 1, 1, 2f / 4, 2f / 3,
            1, 1, 0, 3f / 4, 2f / 3,
            0, 1, 0, 3f / 4, 1f / 3,

            1, 0, 0, 0f / 4, 2f / 3,
            1, 0, 1, 1f / 4, 2f / 3,
            0, 0, 1, 1f / 4, 1f / 3,
            0, 0, 0, 0f / 4, 1f / 3,

            1, 0, 0, 1f / 4, 3f / 3,
            1, 1, 0, 2f / 4, 3f / 3,
            1, 1, 1, 2f / 4, 2f / 3,
            1, 0, 1, 1f / 4, 2f / 3,

            0, 0, 0, 1f / 4, 0f / 3,
            0, 0, 1, 1f / 4, 1f / 3,
            0, 1, 1, 2f / 4, 1f / 3,
            0, 1, 0, 2f / 4, 0f / 3,
    };

    static final int vertexBuffer, programID, blockOffset, fullTransform;

    static {
        vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, combinedVertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        programID = Util.genProgram(
                new Pair<>("/shaders/block-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/block-fs.glsl", GL_FRAGMENT_SHADER)
        );
        blockOffset = glGetUniformLocation(programID, "blockOffset");
        fullTransform = glGetUniformLocation(programID, "fullTransform");
    }

    private final MaterialDatabase materialDatabase;

    public RenderUtils(MaterialDatabase materialDatabase) {
        this.materialDatabase = materialDatabase;
    }

    public static void open(Matrix4f matrixPV, Runnable contents) {
        glBindVertexBuffer(0, vertexBuffer, 0, 20);
        glEnableVertexAttribArray(0);
        glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
        glVertexAttribBinding(0, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
        glVertexAttribBinding(1, 0);
        glUseProgram(programID);
        glUniformMatrix4fv(fullTransform, false, matrixPV.get(new float[16]));

        contents.run();
    }

    public static void setBlockOffset(Vector3f blockOffset) {
        float[] transfer = new float[] {
                blockOffset.x(), blockOffset.y(), blockOffset.z()
        };
        glUniform3fv(RenderUtils.blockOffset, transfer);
    }

    /**
     * draws a frame around the specified chunk
     * @param matrixPV ProjectionView matrix with which the frame is to be rendered
     * @param chunkPos the position of the chunk
     */
    public static void drawChunk(Matrix4f matrixPV, Vector3i chunkPos) {
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

    public static void drawBlockIcon(Material material) {
        Vector3f
                camPos = new Vector3f((float) Math.sqrt(4f/3)),
                camDir = camPos.negate(new Vector3f()),
                right = new Vector3f(-1, 1, 0),
                up = right.cross(camDir);

        Matrix4f matrixPV = new Matrix4f().scale(1, 1, 0.5f).lookAt(camPos, camDir, up);

        Side[] sides = new Side[] {Side.NORTH, Side.WEST, Side.TOP};

        MaterialDatabase materialDatabase = GameRuntime.getInstance().getMaterialDatabase();

        open(matrixPV, () -> {
            materialDatabase.bindMaterial(material);
            for (Side side : sides) {
                glDrawArrays(GL_TRIANGLE_FAN, side.ordinal() * 4, 4);
            }
            materialDatabase.loseMaterial();
        });
    }
}
