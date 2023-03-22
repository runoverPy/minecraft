package game.assets;

import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL46.*;

public class Crosshair {
    private static final float[] vertices = new float[] {
        -1f, -1f, -1f, 0f, 1f,
        1f, -1f, -1f, 1f, 1f,
        1f, 1f, -1f, 1f, 0f,
        -1f, 1f, -1f, 0f, 0f
    };

    public static final int vertexBuffer, texture, program, transform;

    static {
        vertexBuffer = Util.genArrayBuffer(vertices);
        texture = Util.genTexture("/img/reticle.png");
        program = Util.genProgram(
                new Pair<>("/shaders/background-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/background-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
    }

    public Crosshair() {
    }

    public void draw(Matrix4f matrixPV) {
        boolean wasDepthTested = glGetBoolean(GL_DEPTH_TEST);
        if (wasDepthTested) glDisable(GL_DEPTH_TEST);

        glBindVertexBuffer(0, vertexBuffer, 0, 20);
        glEnableVertexAttribArray(0);
        glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
        glVertexAttribBinding(0, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
        glVertexAttribBinding(1, 0);

        glBindTexture(GL_TEXTURE_2D, texture);
        glUseProgram(program);
        glUniformMatrix4fv(transform, false, matrixPV.scale(0.05f, 0.05f, 1f, new Matrix4f()).get(new float[16]));

        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

        if (wasDepthTested) glEnable(GL_DEPTH_TEST);
    }
}
