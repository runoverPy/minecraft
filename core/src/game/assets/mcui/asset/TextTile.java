package game.assets.mcui.asset;

import game.assets.mcui.Align;
import game.assets.mcui.PixelComponent;
import game.assets.text.Glyph;
import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class TextTile extends PixelComponent {
    private Symbol[][] symbols; // todo
    private char[][] chars;
    private Vector4f color;
    private boolean isShaded;
    private Align align;

    public TextTile(String text, Vector4f color) {
        setText(text);
        this.color = color;
        this.align = Align.TOP_LEFT;
    }

    public TextTile(String text) {
        this(text, new Vector4f(1));
    }

    public TextTile() {
        this("");
    }

    public String getText() {
        return String.join("\n", getLines());
    }

    public String[] getLines() {
        String[] output = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            output[i] = new String(chars[i]);
        }
        return output;
    }

    public char[][] getChars() {
        return chars;
    }

    public void setText(String text) {
        String[] lines = text.split("\\n");
        char[][] chars = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            chars[i] = lines[i].toCharArray();
        }
        this.chars = chars;
    }

    public boolean isShaded() {
        return isShaded;
    }

    public void setShaded(boolean shaded) {
        isShaded = shaded;
    }

    public Vector4f getColor() {
        return color;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    private boolean textFits() {
        // TODO: 20.05.23 implement
        return true;
    }

    private int getTextWidth() {
        int width = 0;
        for (char[] line : chars) {
            width += Math.max(width, getLineWidth(line));
        }
        return width;
    }

    private int getTextHeight() {
        int height = chars.length - 1; // todo * lineSpacing();
        for (char[] line : chars) {
            height += getLineHeight(line);
        }
        return height;
    }

    private int getLineWidth(int line) {
        return getLineWidth(chars[line]);
    }

    private int getLineWidth(char[] line) {
        int width = line.length - 1; // todo * charSpacing();
        for (char chr : line) {
            width += Main.getFont().getCharWidth(chr);
        }
        return width;
    }

    private int getLineHeight(int line) {
        return getLineHeight(chars[line]);
    }

    private int getLineHeight(char[] line) {
        int height = 0;
        for (char chr : line) {
            height = Math.max(height, Main.getFont().getCharHeight(chr));
        }
        return height;
    }

    @Override
    public void render(Matrix4f matrix) {
        layoutIfScaleChanged();
        int pxScale = getPxScale();
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        int startXOffset = align.getXOffset(getWidth(), getTextWidth());
        int startYOffset = align.getYOffset(getHeight(), getTextHeight());

        // set advancing matrix
        Matrix4f lineMatrix = new Matrix4f(matrix);
        lineMatrix.translate(
          ((float) (getGlobalX() + startXOffset) / winWidth - 0.5f) * aspectRatio,
          0.5f - (float) (getGlobalY() + startYOffset) / winHeight,
          0
        ).scale(
          (float) pxScale / winHeight,
          (float) pxScale / winHeight,
          0.5f
        );

        for (char[] line : chars) {
            if (isShaded) {
                Matrix4f shadowLineMatrix = lineMatrix.translate(1, -1, 0, new Matrix4f());
                drawLine(shadowLineMatrix, line, new Vector4f(0.25f, 0.25f, 0.25f, 1f));
            }
            drawLine(lineMatrix, line, color);
        }
    }

    private void drawLine(Matrix4f matrix, String string, Vector4f color) {
        drawLine(matrix, string.toCharArray(), color);
    }

    private void drawLine(Matrix4f matrix, char[] line, Vector4f color) {
        for (char c : line) {
            Glyph g = Main.getFont().getGlyph(c);
            Main.getFont().drawGlyph(matrix.scale(g.getW(), g.getH(), 1, new Matrix4f()), c, color);
            matrix.translate(g.getW() + 1, 0, 0);
        }
    }

    private static class MatrixCursor {
        public static MatrixCursor create(Matrix4f matrix, float x, float y) {
            int pxScale = getPxScale();
            int winWidth = Main.getActiveWindow().getWidth();
            int winHeight = Main.getActiveWindow().getHeight();
            float aspectRatio = Main.getActiveWindow().getAspectRatio();

            matrix = new Matrix4f(matrix);
            matrix.translate(
              (x / winWidth - 0.5f) * aspectRatio,
              0.5f - y / winHeight,
              0
            ).scale(
              (float) pxScale / winHeight,
              (float) pxScale / winHeight,
              0.5f
            );

            return new MatrixCursor(matrix);
        }

        private final Matrix4f coreMatrix;
        private Matrix4f lineMatrix;
        private Matrix4f charMatrix;

        public MatrixCursor(Matrix4f matrix) {
            this.coreMatrix = new Matrix4f(matrix);
            this.lineMatrix = new Matrix4f(matrix);
            this.charMatrix = new Matrix4f(matrix);
        }

        public void reset() {
            charMatrix = new Matrix4f(coreMatrix);
            lineMatrix = new Matrix4f(coreMatrix);
        }

        /**
         * basically newline
         * @param pixels
         */
        public void advanceLine(int pixels) {
            lineMatrix.translate(0, pixels, 0);
            charMatrix = new Matrix4f(lineMatrix);
        }

        public void advanceChar(int pixels) {
            charMatrix.translate(pixels, 0, 0);
        }

        public Matrix4f getCharMatrix() {
            return new Matrix4f(charMatrix);
        }
    }

    /**
     * for the future! todo
     */
    private static class Symbol {
        private char c;
        private Vector4f glyphColor, highlightColor;
        private Glyph g;
    }

    public static class TextAccessor {
    }
}
