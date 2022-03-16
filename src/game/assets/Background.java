package game.assets;

import game.main.Main;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public enum Background {
    BRICKS ("/img/bricks.png"),
    STEINLE ("/img/witzig.png"),
    GRAY (0f, 0f, 0f, 0.5f),
    WHITE (1f, 1f, 1f, 1f),
    CLEAR (0f, 0f, 0f, 0f);

    private final int texture;

    private static final int vertices, program, transform;

    private static final float[] backgroundVertices = new float[] {
            0, 0, -1, 1f/4, 0f/3,
            1, 0, -1, 2f/4, 0f/3,
            1, 1, -1, 2f/4, 1f/3,
            0, 1, -1, 1f/4, 1f/3,
    };

    static {
        vertices = Util.genArrayBuffer(backgroundVertices);

        program = Util.genProgram(
                new Pair<>("/shaders/background-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/background-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
    }

    Background(String filePath) {
        texture = Util.genTexture(filePath);
    }

    Background(float r, float g, float b, float a) {
        texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexImage2D(
                GL_TEXTURE_2D,
                0,
                GL_RGBA,
                1,
                1,
                0,
                GL_RGBA,
                GL_FLOAT,
                new float[] {r, g, b, a} // a very fun hack
        );
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    }

    public void render(Matrix4f matrixPV) {
        glBindVertexBuffer(0, vertices, 0, 20);
        glEnableVertexAttribArray(0);
        glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
        glVertexAttribBinding(0, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
        glVertexAttribBinding(1, 0);

        glUseProgram(program);
        glBindTexture(GL_TEXTURE_2D, texture);

        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();

        for (int y = -2; y <= 2; y++) {
            int factor = (int) Math.ceil((float) winWidth / winHeight);
            for (int x = -2 * factor; x <= 2 * factor; x++) {
                glUniformMatrix4fv(transform, false, matrixPV
                        .scale(0.5f, 0.5f, 1, new Matrix4f())
                        .translate(x, y, 0)
                        .get(new float[16]));
                glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
            }
        }

        glUseProgram(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}