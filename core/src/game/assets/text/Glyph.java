package game.assets.text;

import org.joml.Matrix4f;
import org.joml.Vector4f;

public class Glyph {
    private static final float[] glyphVertices = new float[]{
      0, -1, -1,
      1, -1, -1,
      0, 0, -1,
      1, 0, -1,
    };

    private final int w, h;
    private final int index;

    Glyph(int w, int h, int index) {
        this.w = w;
        this.h = h;
        this.index = index;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getIndex() {
        return index;
    }

    public void draw(Matrix4f matrix, Vector4f color) {

    }
}
