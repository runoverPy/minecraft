package game.assets.widgets;

import game.assets.boxes.Box;
import game.main.Main;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL46.*;

class ColorBox extends ChildBox {
    private static final float[] buttonVertices = new float[] {
            -1, -1, -1,
            1, -1, -1,
            -1, 1, -1,
            1, 1, -1,
    };

    public static int vertices, program, transform, color;

    static {
        vertices = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertices);
        glBufferData(GL_ARRAY_BUFFER, buttonVertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        program = Util.genProgram(
                new Pair<>("/shaders/button-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/button-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
        color = glGetUniformLocation(program, "color_in");
    }

    public static void draw(int width, int height, int xOffset, int yOffset, Box parent, Vector4f boxColor, int pxScale, Matrix4f matrix4f) {
        new ColorBox(width, height, xOffset, yOffset, parent, boxColor).draw(pxScale, matrix4f);
    }

    private final Vector4f boxColor;

    public ColorBox(int width, int height, int xOffset, int yOffset, Box parent, Vector4f boxColor) {
        super(width, height, xOffset, yOffset, parent);
        this.boxColor = boxColor;
    }

    public void draw(int pxScale, Matrix4f matrix4f) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        glUseProgram(program);
        glUniformMatrix4fv(transform, false, matrix4f
                .translate(
                        ((float) getCenterX(pxScale) / winWidth - 0.5f) * aspectRatio,
                        0.5f - (float) getCenterY(pxScale) / winHeight,
                        0,
                        new Matrix4f()
                ).scale(
                        (float) getWidth(pxScale) / winHeight / 2,
                        (float) getHeight(pxScale) / winHeight / 2,
                        0.5f
                ).get(new float[16])
        );

        glUniform4fv(color, new float[] {
                boxColor.x(), boxColor.y(), boxColor.z(), boxColor.w()
        });

        glBindBuffer(GL_ARRAY_BUFFER, vertices);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0L);

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    }
}