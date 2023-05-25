package game.assets.ui_elements.asset;

import game.assets.text.ProportionalFont;
import game.assets.text.Glyph;
import game.assets.ui_elements.ChildBox;
import game.assets.ui_elements.Component;
import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class TextBox extends ChildBox {
    private String text;
    private final boolean centered;
    private final boolean shaded;

    public TextBox(int width, int height, int xOffset, int yOffset, Component parent, String text, boolean centered, boolean shaded) {
        super(width, height, xOffset, yOffset, parent);
        this.text = text;
        this.centered = centered;
        this.shaded = shaded;
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        int fullLength = text.length() - 1;
        for (char chr : text.toCharArray()) {
            fullLength += Main.getFont().getCharWidth(chr);
        }

        int startXOffset = centered ? (int) Math.ceil((getWidth() - fullLength) / 2f) * pxScale : 0;
        int startYOffset = (int) Math.ceil((getHeight() - ProportionalFont.chrHeight) / 2f) * pxScale;

        // set advancing matrix
        Matrix4f lineMatrix = new Matrix4f(matrix4f);
        lineMatrix.translate(
                ((float) (getCornerX(pxScale) + startXOffset) / winWidth - 0.5f) * aspectRatio,
                0.5f - (float) (getCornerY(pxScale) + startYOffset) / winHeight,
                0
        ).scale(
                (float) pxScale / winHeight,
                (float) pxScale / winHeight,
                0.5f
        );

        if (shaded) {
            Matrix4f shadowLineMatrix = lineMatrix.translate(1, -1, 0, new Matrix4f());
            drawLine(shadowLineMatrix, text, new Vector4f(0.25f, 0.25f, 0.25f, 1f));
        }
        drawLine(lineMatrix, text, new Vector4f(1));
    }

    private void drawLine(Matrix4f matrix, String string, Vector4f color) {
        for (char c : string.toCharArray()) {
            Glyph g = Main.getFont().getGlyph(c);
            Main.getFont().drawGlyph(matrix.scale(g.getW(), g.getH(), 1, new Matrix4f()), c, color);
            matrix.translate(g.getW() + 1, 0, 0);
        }
    }
}
