package game.assets.text;

import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class TextField {
    private final Advance advance;
    private final Vector4f color;
    private final int cornerX, cornerY;
    private String contents;
    private final boolean shaded;

    public TextField(boolean shaded, int cornerX, int cornerY, Advance advance, Vector4f color) {
        this.advance = advance;
        this.color = color;
        this.shaded = shaded;
        this.cornerX = cornerX;
        this.cornerY = cornerY;
        this.contents = "";
    }

    public void print(String s) {
        contents += s;
    }

    public void println(String s) {
        print(s + "\n");
    }

    public void printf(String format, Object... args) {
        print(String.format(format, args));
    }

    public void printlnf(String format, Object... args) {
        print(String.format(format, args) + "\n");
    }

    public int getCornerX() {
        return cornerX;
    }

    public int getCornerY() {
        return cornerY;
    }

    public void draw(int pxScale, Matrix4f matrix4f) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        Matrix4f fieldMatrix = new Matrix4f(matrix4f).translate(
                ((float) getCornerX() / winWidth - 0.5f) * aspectRatio,
                0.5f - (float) getCornerY() / winHeight,
                0
        ).scale(
                (float) pxScale / winHeight,
                (float) pxScale / winHeight,
                0.5f
        );

        String[] lines = contents.split("\\n");

        int textHeight = (ProportionalFont.chrHeight + 1) * lines.length - 1;
        fieldMatrix.translate(0, advance.getYAdvance() * textHeight, 0);

        for (String line : lines) {
            int lineLength = line.length() - 1;
            for (char c : line.toCharArray()) {
                lineLength += Main.getFont().getCharWidth(c);
            }
            Matrix4f lineMatrix = new Matrix4f(fieldMatrix);
            lineMatrix.translate(advance.getXAdvance() * lineLength, 0, 0);

            if (shaded) {
                Matrix4f shadowLineMatrix = lineMatrix.translate(1, -1, 0, new Matrix4f());

                for (char c : line.toCharArray()) {
                    ProportionalFont.Glyph g = Main.getFont().getGlyph(c);
                    Matrix4f charMatrix = shadowLineMatrix.scale(g.getW(), g.getH(), 1, new Matrix4f());
                    Main.getFont().drawGlyph(charMatrix, c, new Vector4f(0.5f, 0.5f, 0.5f, 1f));
                    shadowLineMatrix.translate(g.getW() + 1, 0, 0);
                }
            }

            for (char c : line.toCharArray()) {
                ProportionalFont.Glyph g = Main.getFont().getGlyph(c);
                Matrix4f charMatrix = lineMatrix.scale(g.getW(), g.getH(), 1, new Matrix4f());
                Main.getFont().drawGlyph(charMatrix, c, color);
                lineMatrix.translate(g.getW() + 1, 0, 0);
            }

            fieldMatrix.translate(0, -ProportionalFont.chrHeight + 1, 0);
        }
        contents = "";
    }
}
