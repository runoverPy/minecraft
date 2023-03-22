package game.assets.mcui.asset;

import game.assets.mcui.PixelComponent;
import game.assets.ui_elements.Component;
import game.main.Main;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public class ColorFrame extends PixelComponent {
    private static final float[] tileVertices = new float[]{
      0, 0, -1, 0, 0,
      0, -1, -1, 0, 1,
      1, -1, -1, 1, 1,
      1, 0, -1, 1, 0,
    };
    protected static final int vertices, program, transform, color;

    static {
        vertices = Util.genArrayBuffer(tileVertices);
        program = Util.genProgram(
          new Pair<>("/shaders/button-vs.glsl", GL_VERTEX_SHADER),
          new Pair<>("/shaders/button-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
        color = glGetUniformLocation(program, "color_in");
    }

    private Vector4f boxColor;

    public ColorFrame(Vector4f boxColor) {
        this.boxColor = boxColor;
    }

    public Vector4f getBoxColor() {
        return new Vector4f(boxColor);
    }

    public void setBoxColor(Vector4f boxColor) {
        this.boxColor = boxColor;
    }

    @Override
    public void render(Matrix4f matrix4f, int cornerX, int cornerY) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        glUseProgram(program);
        glUniformMatrix4fv(transform, false, matrix4f
          .translate(
            ((float) cornerX / winWidth - 0.5f) * aspectRatio,
            0.5f - (float) cornerY / winHeight,
            0,
            new Matrix4f()
          ).scale(
            (float) getWidth() / winHeight,
            (float) getHeight() / winHeight,
            0.5f
          ).get(new float[16])
        );

        glUniform4fv(color, new float[] {
          boxColor.x(), boxColor.y(), boxColor.z(), boxColor.w()
        });

        glBindBuffer(GL_ARRAY_BUFFER, vertices);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 20, 0L);

        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
    }
}
