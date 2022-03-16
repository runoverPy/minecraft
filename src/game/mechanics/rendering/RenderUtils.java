package game.mechanics.rendering;

import game.util.Util;
import game.util.Fn;

import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL43.*;

class RenderUtils implements AutoCloseable {
    private static final float[] combinedVertices = new float[]{
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

    private static final int vertexBuffer, programID, blockOffset, fullTransform;

    static {
        vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, combinedVertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        programID = Util.genProgram(
                new Pair<>("/shaders/block-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/block-fs.glsl", GL_FRAGMENT_SHADER)
        );
        blockOffset = glGetUniformLocation(programID, "blockOffset");
        fullTransform = glGetUniformLocation(programID, "fullTransform");
    }

    public static RenderUtils open(Matrix4f matrixPV) {
        glBindVertexBuffer(0, vertexBuffer, 0, 20);
        glEnableVertexAttribArray(0);
        glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
        glVertexAttribBinding(0, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
        glVertexAttribBinding(1, 0);
        glUseProgram(programID);
        glUniformMatrix4fv(fullTransform, false, matrixPV.get(new float[16]));
        return null;
    }

    public static void open(Matrix4f matrixPV, Fn contents) {
        glBindVertexBuffer(0, vertexBuffer, 0, 20);
        glEnableVertexAttribArray(0);
        glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
        glVertexAttribBinding(0, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
        glVertexAttribBinding(1, 0);
        glUseProgram(programID);
        glUniformMatrix4fv(fullTransform, false, matrixPV.get(new float[16]));

        contents.call();
    }

    @Override
    public void close() {

    }

    public static void setBlockOffset(Vector3f blockOffset) {
        float[] transfer = new float[] {
                blockOffset.x(), blockOffset.y(), blockOffset.z()
        };
        glUniform3fv(RenderUtils.blockOffset, transfer);
    }
}
