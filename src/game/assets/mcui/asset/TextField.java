package game.assets.mcui.asset;

import game.assets.mcui.PixelComponent;
import game.assets.text.ProportionalFont;
import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class TextField extends PixelComponent {
    private StringBuffer text;
    private Vector4f color;
    private boolean isShaded, isCentered;

    public TextField(StringBuffer text, Vector4f color) {
        this.text = text;
        this.color = color;
    }

    public TextField(StringBuffer text) {
        this(text, new Vector4f(1));
    }

    public TextField(String text, Vector4f color) {
        this(new StringBuffer(text), color);
    }

    public TextField(String text) {
        this(text, new Vector4f(1));
    }

    public String getText() {
        return text.toString();
    }

    public StringBuffer getTextBuffer() {
        return text;
    }

    public void setTextBuffer(StringBuffer newTextBuffer) {
        this.text = newTextBuffer;
    }

    @Override
    public void render(Matrix4f matrix4f, int cornerX, int cornerY) {
        int pxScale = getPxScale();
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        int fullLength = text.length() - 1;
        for (char chr : text.toString().toCharArray()) {
            fullLength += Main.getFont().getCharWidth(chr);
        }

        int startXOffset = isCentered ? (int) Math.ceil((getWidth() - fullLength) / 2f) * pxScale : 0;
        int startYOffset = 0; // (int) Math.ceil((getHeight() - ProportionalFont.chrHeight) / 2f) * pxScale;

        // set advancing matrix
        Matrix4f lineMatrix = new Matrix4f(matrix4f);
        lineMatrix.translate(
          ((float) (cornerX + startXOffset) / winWidth - 0.5f) * aspectRatio,
          0.5f - (float) (cornerY + startYOffset) / winHeight,
          0
        ).scale(
          (float) pxScale / winHeight,
          (float) pxScale / winHeight,
          0.5f
        );

        if (isShaded) {
            Matrix4f shadowLineMatrix = lineMatrix.translate(1, -1, 0, new Matrix4f());
            drawLine(shadowLineMatrix, text.toString(), new Vector4f(0.25f, 0.25f, 0.25f, 1f));
        }
        drawLine(lineMatrix, text.toString(), color);
    }

    private void drawLine(Matrix4f matrix, String string, Vector4f color) {
        for (char c : string.toCharArray()) {
            ProportionalFont.Glyph g = Main.getFont().getGlyph(c);
            Main.getFont().drawGlyph(matrix.scale(g.getW(), g.getH(), 1, new Matrix4f()), c, color);
            matrix.translate(g.getW() + 1, 0, 0);
        }
    }

    /**
     * for the future!
     */
    private static class Symbol {
        private char c;
        private Vector4f glyphColor, highlightColor;
        private ProportionalFont.Glyph g;
    }
}
