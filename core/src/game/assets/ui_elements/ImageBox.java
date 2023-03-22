package game.assets.ui_elements;

import game.main.Main;
import game.util.Image;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL43.*;

public class ImageBox extends TileBox {
    protected static final int program;
    protected static final int transform;

    static {
        program = Util.genProgram(
          new Pair<>("/shaders/background-vs.glsl", GL_VERTEX_SHADER),
          new Pair<>("/shaders/background-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
    }

    private final Image image;
    private final int texture;

    public ImageBox(int width, int height, int xOffset, int yOffset, Component parent, Image image) {
        super(width, height, xOffset, yOffset, parent);
        this.image = image;
        texture = Util.genTexture(image);
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        int horizontalImageCount = getWidth() / image.getWidth();
        int verticalImageCount = getHeight() / image.getHeight();

        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        glBindVertexBuffer(0, vertices, 0, 20);
        glEnableVertexAttribArray(0);
        glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
        glVertexAttribBinding(0, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
        glVertexAttribBinding(1, 0);

        glUseProgram(program);
        glBindTexture(GL_TEXTURE_2D, texture);

        try (Scissor _scissor = Scissor.cut(
          getCornerX(pxScale),
          winHeight - getCornerY(pxScale) - getHeight(pxScale),
          getWidth(pxScale),
          getHeight(pxScale)
        )) {
            for (int y = 0; y <= verticalImageCount; y++) {
                for (int x = 0; x <= horizontalImageCount; x++) {
                    glUniformMatrix4fv(transform, false, new Matrix4f(matrix4f)
                      .translate(
                        ((float) (getCornerX(pxScale) + image.getWidth() * x * pxScale) / winWidth - 0.5f) * aspectRatio,
                        0.5f - (float) (getCornerY(pxScale) + image.getHeight() * y * pxScale) / winHeight,
                        0
                      ).scale(
                        (float) image.getWidth() * pxScale / winHeight,
                        (float) image.getHeight() * pxScale / winHeight,
                        0.5f
                      ).get(new float[16])
                    );
                    glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
                }
            }
        }

        glUseProgram(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
