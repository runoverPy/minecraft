package game.assets.mcui.asset;

import game.assets.mcui.Align;
import game.assets.mcui.PixelComponent;
import game.assets.text.Glyph;
import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.io.IOException;
import java.util.function.Function;

public class TextTile extends PixelComponent {
    private Symbol[][] symbols; // todo
    private char[][] chars;
    private Vector4f color, shade;
    private boolean isShaded;
    private Align align;

    public TextTile(String text, Vector4f color, Vector4f shade) {
        setText(text);
        this.color = color;
        this.shade = shade;
        this.align = Align.TOP_LEFT;
    }

    public TextTile(String text) {
        this(text, new Vector4f(1), new Vector4f(0.25f, 0.25f, 0.25f, 1f));
    }

    public TextTile() {
        this("");
    }

    private String getText() {
        return String.join("\n", getLines());
    }

    private String[] getLines() {
        String[] output = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            output[i] = new String(chars[i]);
        }
        return output;
    }

    public void setText(String text) {
        String[] lines = text.split("\\n", -1);
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

    public Vector4f getShade() {
        return shade;
    }

    public void setShade(Vector4f shade) {
        this.shade = shade;
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
        if (isShaded)
            width++;
        return width * getPxScale();
    }

    private int getLineHeight(int line) {
        return getLineHeight(chars[line]);
    }

    private int getLineHeight(char[] line) {
        int height = 0;
        for (char chr : line) {
            height = Math.max(height, Main.getFont().getCharHeight(chr));
        }
        if (isShaded)
            height++;
        return height * getPxScale();
    }

    @Override
    public void render(Matrix4f matrix) {
        layoutIfScaleChanged();
        int startYOffset = getGlobalY() + align.getYOffset(getHeight(), getTextHeight());

        MatrixCursor cursor = MatrixCursor.create(matrix, getGlobalX(), startYOffset);

        if (isShaded) {
            MatrixCursor shadow = cursor.offset(1, -1);
            drawText(shadow, shade);
        }
        drawText(cursor, color);
    }

    private void drawText(MatrixCursor cursor, Vector4f color) {
        for (char[] line : chars) {
            int lineHeight = 0;
            cursor.translateX(align.getXOffset(getWidth(), getLineWidth(line)));
            for (char c : line) {
                Glyph g = Main.getFont().getGlyph(c);
                Matrix4f charMatrix = cursor.getCharMatrix().scale(g.getW(), g.getH(), 1, new Matrix4f());
                Main.getFont().drawGlyph(charMatrix, c, color);
                cursor.advanceChar(g.getW() + 1);
                lineHeight = Math.max(lineHeight, Main.getFont().getCharHeight(c));
            }
            cursor.advanceLine(lineHeight);
        }
    }

    private static class MatrixCursor {
        public static MatrixCursor create(Matrix4f matrix, float x, float y) {
            int pxScale = getPxScale();
            int winWidth = Main.getActiveWindow().getWidth();
            int winHeight = Main.getActiveWindow().getHeight();

            matrix = new Matrix4f(matrix);
            matrix.translate(
              (x / winWidth - 0.5f) * ((float) winWidth / winHeight),
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

        /**
         * basically newline
         * @param pixels
         */
        public void advanceLine(int pixels) {
            lineMatrix.translate(0, -pixels, 0);
            charMatrix = new Matrix4f(lineMatrix);
        }

        public void advanceChar(int pixels) {
            charMatrix.translate(pixels, 0, 0);
        }

        public Matrix4f getCharMatrix() {
            return new Matrix4f(charMatrix);
        }

        public MatrixCursor offset(int x, int y) {
            return new MatrixCursor(coreMatrix.translate(x, y, 0, new Matrix4f()));
        }

        public void translate(int x, int y) {
            float pxScale = getPxScale();
            charMatrix.translate(x / pxScale, y / pxScale, 0);
        }

        public void translateX(int x) {
            translate(x, 0);
        }

        public void translateY(int y) {
            translate(0, y);
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

    public TextAccessor edit() {
        return new TextAccessor();
    }

    public class TextAccessor {
        public void append(CharSequence csq) {
            getThenSet(s -> s + csq);
        }

        public void append(CharSequence csq, int start, int end) {

        }

        public void append(char c) throws IOException {

        }

        public void insert(char c, int index) {

        }

        public void insert(CharSequence seq, int index, int length) {

        }

        public void insert(CharSequence seq, int index, int offset, int length) {

        }

        public void delete(int index) {

        }

        public void delete(int start, int end) {

        }

        public TextAccessor clear() {
            setText("");
            return this;
        }

        public TextAccessor print(String string) {
            getThenSet(s -> s + string);
            return this;
        }

        public TextAccessor println(String string) {
            return print(string + "\n");
        }

        public TextAccessor printf(String format, Object... args) {
            return print(String.format(format, args));
        }

        public TextAccessor newline() {
            return println("");
        }

        @Override
        public String toString() {
            return TextTile.this.getText();
        }

        public void getThenSet(Function<String, String> conversion) {
            setText(conversion.apply(getText()));
        }
    }
}
