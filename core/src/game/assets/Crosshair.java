package game.assets;

import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL46.*;

public class Crosshair {
    private static final float[] vertices = new float[] {
        -1f, -1f, -1f, 0f, 1f,
        1f, -1f, -1f, 1f, 1f,
        1f, 1f, -1f, 1f, 0f,
        -1f, 1f, -1f, 0f, 0f
    };

    private static final float[] compass = new float[] {
      0f, 0f, 0f, 1f, 0f, 0f,
      1f, 0f, 0f, 1f, 0f, 0f,
      0f, 0f, 0f, 0f, 1f, 0f,
      0f, 1f, 0f, 0f, 1f, 0f,
      0f, 0f, 0f, 0f, 0f, 1f,
      0f, 0f, 1f, 0f, 0f, 1f
    };

    public static final int vertexBuffer, compassBuffer, texture, program0, program1, transform0, transform1;

    static {
        vertexBuffer = Util.genArrayBuffer(vertices);
        texture = Util.genTexture("/img/reticle.png");
        program0 = Util.genProgram(
          new Pair<>("/shaders/background-vs.glsl", GL_VERTEX_SHADER),
          new Pair<>("/shaders/background-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform0 = glGetUniformLocation(program0, "transform");
        compassBuffer = Util.genArrayBuffer(compass);
        program1 = Util.genProgram(
          new Pair<>("/shaders/compass-vs.glsl", GL_VERTEX_SHADER),
//          new Pair<>("/shaders/compass-gs.glsl", GL_GEOMETRY_SHADER),
          new Pair<>("/shaders/compass-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform1 = glGetUniformLocation(program1, "fullTransform");
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
        glUseProgram(program0);
        glUniformMatrix4fv(transform0, false, matrixPV.scale(0.05f, 0.05f, 1f, new Matrix4f()).get(new float[16]));

        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

        if (wasDepthTested) glEnable(GL_DEPTH_TEST);
    }

    public void drawCompass(Matrix4f matrix, double h, double v) {
        float dist = 10;

        Vector3f
          ahead = new Vector3f(
          (float) (Math.cos(v) * Math.cos(h)),
          (float) (Math.cos(v) * Math.sin(h)),
          (float) Math.sin(v)),
          viewPos = new Vector3f(ahead).mul(dist).negate(),
          right = new Vector3f(
            (float) Math.sin(h),
            (float) -Math.cos(h),
            (float) 0),
          up = right.cross(ahead);

        matrix = new Matrix4f(matrix)
          .lookAt(viewPos, ahead, up);

        drawCompass(matrix);
    }

    public void drawCompass(Matrix4f viewMatrix) {
        boolean wasDepthTested = glGetBoolean(GL_DEPTH_TEST);
        if (wasDepthTested) glDisable(GL_DEPTH_TEST);
        int prevLineWidth = glGetInteger(GL_LINE_WIDTH);
        glLineWidth(2);

        glBindVertexBuffer(0, compassBuffer, 0, 24);
        glEnableVertexAttribArray(0);
        glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
        glVertexAttribBinding(0, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribFormat(1, 3, GL_FLOAT, false, 3 * 4);
        glVertexAttribBinding(1, 0);

        glUseProgram(program1);
        glUniformMatrix4fv(transform1, false, viewMatrix.get(new float[16]));

        glDrawArrays(GL_LINES, 0, 6);

        glLineWidth(prevLineWidth);
        if (wasDepthTested) glEnable(GL_DEPTH_TEST);
    }
}
