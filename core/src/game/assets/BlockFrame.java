package game.assets;

import game.util.Side;
import game.util.Util;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;


public class BlockFrame {
    private static final float[] vertices = new float[]{
            0, 0, 0,
            0, 0, 1,
            0, 1, 0,
            0, 1, 1,
            1, 0, 0,
            1, 0, 1,
            1, 1, 0,
            1, 1, 1,
    };
    private static final int[] cubeIndices = new int[]{
            0, 1,
            0, 2,
            0, 4,
            1, 3,
            1, 5,
            2, 3,
            2, 6,
            4, 5,
            4, 6,
            3, 7,
            5, 7,
            6, 7,
    };
    private static final int[] sideIndices = new int[] {
            1, 3, 7, 5,
            0, 2, 6, 4,
            2, 3, 7, 6,
            0, 1, 5, 4,
            4, 5, 7, 6,
            0, 1, 3, 2
    };
    private static final int vertexBuffer, cubeIndexBuffer, sideIndexBuffer, programID, fullTransform, frameColor;


    static {
        vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        cubeIndexBuffer = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeIndexBuffer);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, cubeIndices, GL_STATIC_DRAW);
        sideIndexBuffer = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, sideIndexBuffer);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, sideIndices, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        programID = Util.genProgram(
                new Pair<>("/shaders/cubeframe-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/cubeframe-fs.glsl", GL_FRAGMENT_SHADER)
        );
        fullTransform = glGetUniformLocation(programID, "fullTransform");
        frameColor = glGetUniformLocation(programID, "frameColor");
    }

    public static void draw(Matrix4f matrixPV, Vector3f pos) {
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeIndexBuffer);

        glUseProgram(programID);
        glUniformMatrix4fv(fullTransform, false, matrixPV.translate(pos, new Matrix4f()).get(new float[16]));
        glUniform4fv(frameColor, new float[] {0, 0, 0, 0.5f});

        glDrawElements(GL_LINES, 24, GL_UNSIGNED_INT, NULL);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public static void draw(Matrix4f matrixPV, Vector3f pos, Vector4f color) {
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeIndexBuffer);

        glUseProgram(programID);
        glUniformMatrix4fv(fullTransform, false, matrixPV.translate(pos, new Matrix4f()).get(new float[16]));
        glUniform4fv(frameColor, new float[] {
                color.x, color.y, color.z, color.w
        });

        glDrawElements(GL_LINES, 24, GL_UNSIGNED_INT, NULL);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public static void draw(Matrix4f matrixPV, Vector3f pos, Side side) {
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, sideIndexBuffer);

        glUseProgram(programID);
        glUniformMatrix4fv(fullTransform, false, matrixPV.translate(pos, new Matrix4f()).get(new float[16]));
        glUniform4fv(frameColor, new float[] {1f, 0f, 0f, 1f});

        glDrawElements(GL_LINE_LOOP, 4, GL_UNSIGNED_INT, 16L * side.ordinal());

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
