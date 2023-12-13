package game.assets.mcui.atoms;

import game.assets.GLUtils;
import game.assets.mcui.PixelComponent;
import game.main.Main;
import game.util.Image;
import game.util.ImageFile;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL43.*;

public class PixelImageTile extends PixelComponent {
    private static final int program, transform;

    static {
        program = Util.genProgram(
          new Pair<>("/shaders/background-vs.glsl", GL_VERTEX_SHADER),
          new Pair<>("/shaders/background-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
    }

    protected final Image image;
    protected final int texture;

    public PixelImageTile(Image image) {
        this.image = image;
        this.texture = Util.genTexture(image);
    }

    @Override
    public void render(Matrix4f matrix) {
        layoutIfScaleChanged();
        int pxScale = getPxScale();
        int horizontalImageCount = (int) Math.ceil((float) getPxWidth() / image.getWidth());
        int verticalImageCount = (int) Math.ceil((float) getPxHeight() / image.getHeight());

        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        glBindVertexBuffer(0, Tile.vertices, 0, 20);
        glEnableVertexAttribArray(0);
        glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
        glVertexAttribBinding(0, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
        glVertexAttribBinding(1, 0);

        glUseProgram(program);
        glBindTexture(GL_TEXTURE_2D, texture);

        try (GLUtils.GLOP ignored = GLUtils.scissor(getGlobalX(), getGlobalY(), getWidth(), getHeight())) {
            for (int y = 0; y < verticalImageCount; y++) {
                for (int x = 0; x < horizontalImageCount; x++) {
                    glUniformMatrix4fv(transform, false, new Matrix4f(matrix)
                      .translate(
                        ((float) (getGlobalX() + image.getWidth() * x * pxScale) / winWidth - 0.5f) * aspectRatio,
                        0.5f - (float) (getGlobalY() + image.getHeight() * y * pxScale) / winHeight,
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

    @Override
    public String toString() {
        return super.toString() + "[" +
          (image instanceof ImageFile ? ((ImageFile) image).getFilePath() : "null")
          + "]";
    }
}
