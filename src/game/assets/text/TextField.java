package game.assets.text;

import game.assets.PixelWidget;
import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;

public class TextField extends PixelWidget {
    private final Matrix4f matrixPV;
    private final float scale;
    private final Advance advance;
    private final Vector4f color;

    public TextField(Matrix4f matrixPV, float scale, Vector3f anchor, Align align, Advance advance) {
        this(matrixPV, scale, anchor, align, advance, new Vector4f(1f, 1f, 1f, 1f));
    }

    public TextField(Matrix4f matrixPV, float scale, Vector3f anchor, Align align, Advance advance, Vector4f color) {
        super(11);
        this.matrixPV = matrixPV.translate(anchor.add(0, scale * align.getYOffset(), 0), new Matrix4f());
        this.scale = getSizeForPixels(scale);
        this.advance = advance;
        this.color = color;
    }

    public void print(String text) {
        Matrix4f lineMatrix = matrixPV.scale(AsciiGlyphMap.aspectRatio * scale, scale, 1, new Matrix4f());
        lineMatrix.translate(advance.getXAdvance() * text.length(), 0, 0);
        for (char c : text.toCharArray()) {
            AsciiGlyphMap.drawGlyph(lineMatrix, c, color);
            lineMatrix.translate(1, 0, 0);
        }
        flush();
    }

    public void flush() {
        matrixPV.translate(0, advance.getYAdvance() * scale, 0);
    }
}
