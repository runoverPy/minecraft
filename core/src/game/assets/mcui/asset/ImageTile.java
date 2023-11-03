package game.assets.mcui.asset;

import game.assets.mcui.Component;
import game.main.Main;
import game.util.Image;
import game.util.ImageFile;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL43.*;

public class ImageTile extends Component {
    private static final int program, transform;

    static {
        program = Util.genProgram(
          new Pair<>("/shaders/background-vs.glsl", GL_VERTEX_SHADER),
          new Pair<>("/shaders/background-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
    }

    private final Image image;
    private final int texture;

    public ImageTile(Image image) {
        this.image = image;
        this.texture = Util.genTexture(image);
    }

    @Override
    public int calcWidth() {
        return image.getWidth();
    }

    @Override
    public int calcHeight() {
        return image.getHeight();
    }

    @Override
    public void render(Matrix4f matrix) {
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

        glUniformMatrix4fv(transform, false, new Matrix4f(matrix)
          .translate(
            ((float) getGlobalX() / winWidth - 0.5f) * aspectRatio,
            0.5f - (float) getGlobalY() / winHeight,
            0
          ).scale(
            (float) getWidth() / winHeight,
            (float) getHeight() / winHeight,
            0.5f
          ).get(new float[16])
        );
        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

    }
}
