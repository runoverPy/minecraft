package game.assets.widgets;

import game.assets.text.AsciiGlyphMap;
import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class TextBox extends ChildBox {
    private final String text;
    private final boolean centered, shaded;

    public TextBox(int width, int height, int xOffset, int yOffset, Box parent, String text, boolean centered) {
        this(width, height, xOffset, yOffset, parent, text, centered, false);
    }

    public TextBox(int width, int height, int xOffset, int yOffset, Box parent, String text, boolean centered, boolean shaded) {
        super(width, height, xOffset, yOffset, parent);
        this.text = text;
        this.centered = centered;
        this.shaded = shaded;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        int visibleLength = (int) Math.ceil((float) width / AsciiGlyphMap.charWidth);
        String visibleString = text.substring(Math.max(0, text.length() - visibleLength));

        float startXOffset = centered ? (float) Math.ceil((width - text.length() * AsciiGlyphMap.charWidth) / 2f) * pxScale : 0;
        float startYOffset = (float) Math.ceil((height - AsciiGlyphMap.charHeight) / 2f) * pxScale;

        // set advancing matrix
        Matrix4f lineMatrix = new Matrix4f(matrix4f);
        lineMatrix.translate(
                ((getCornerX(pxScale) + startXOffset) / winWidth - 0.5f) * aspectRatio,
                0.5f - (getCornerY(pxScale) + startYOffset + AsciiGlyphMap.charHeight * pxScale) / winHeight,
                0
        ).scale(
                (float) AsciiGlyphMap.charWidth * pxScale / winHeight,
                (float) AsciiGlyphMap.charHeight * pxScale / winHeight,
                0.5f
        );

        if (shaded) {
            Matrix4f shadowLineMatrix = new Matrix4f(matrix4f);
            shadowLineMatrix.translate(
                    ((getCornerX(pxScale) + startXOffset + pxScale) / winWidth - 0.5f) * aspectRatio,
                    0.5f - (getCornerY(pxScale) + startYOffset + pxScale + AsciiGlyphMap.charHeight * pxScale) / winHeight,
                    0
            ).scale(
                    (float) AsciiGlyphMap.charWidth * pxScale / winHeight,
                    (float) AsciiGlyphMap.charHeight * pxScale / winHeight,
                    0.5f
            );

            for (char c : visibleString.toCharArray()) {
                AsciiGlyphMap.drawGlyph(shadowLineMatrix, c, new Vector4f(0.5f, 0.5f, 0.5f, 1f));
                shadowLineMatrix.translate(1, 0, 0);
            }
        }

        for (char c : visibleString.toCharArray()) {
            AsciiGlyphMap.drawGlyph(lineMatrix, c);
            lineMatrix.translate(1, 0, 0);
        }
    }
}
