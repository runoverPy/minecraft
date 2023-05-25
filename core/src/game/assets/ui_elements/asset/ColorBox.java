package game.assets.ui_elements.asset;

import game.assets.mcui.asset.Tile;
import game.assets.ui_elements.ChildBox;
import game.assets.ui_elements.Component;
import game.main.Main;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL46.*;

public class ColorBox extends ChildBox {
    protected static final int program;
    protected static final int transform;
    private static final int color;

    static {
        program = Util.genProgram(
                new Pair<>("/shaders/button-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/button-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
        color = glGetUniformLocation(program, "color_in");
    }

    private Vector4f boxColor;

    public ColorBox(int width, int height, int xOffset, int yOffset, Component parent, Vector4f boxColor) {
        super(width, height, xOffset, yOffset, parent);
        this.boxColor = boxColor;
    }

    public Vector4f getBoxColor() {
        return new Vector4f(boxColor);
    }

    public void setBoxColor(Vector4f boxColor) {
        this.boxColor = boxColor;
    }

    public void draw(int pxScale, Matrix4f matrix4f) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        glUseProgram(program);
        glUniformMatrix4fv(transform, false, matrix4f
                .translate(
                        ((float) getCornerX(pxScale) / winWidth - 0.5f) * aspectRatio,
                        0.5f - (float) getCornerY(pxScale) / winHeight,
                        0,
                        new Matrix4f()
                ).scale(
                        (float) getWidth(pxScale) / winHeight,
                        (float) getHeight(pxScale) / winHeight,
                        0.5f
                ).get(new float[16])
        );

        glUniform4fv(color, new float[] {
                boxColor.x(), boxColor.y(), boxColor.z(), boxColor.w()
        });

        glBindBuffer(GL_ARRAY_BUFFER, Tile.vertices);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 20, 0L);

        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
    }
}