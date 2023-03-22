package game.assets.ui_elements;

import game.util.Util;
import org.javatuples.Pair;

import static org.lwjgl.opengl.GL20.*;

abstract class TileBox extends ChildBox {
    private static final float[] tileVertices = new float[]{
      0, 0, -1, 0, 0,
      0, -1, -1, 0, 1,
      1, -1, -1, 1, 1,
      1, 0, -1, 1, 0,
    };
    protected static final int vertices;

    static {
        vertices = Util.genArrayBuffer(tileVertices);
    }

    public TileBox(int width, int height, int xOffset, int yOffset, Component parent) {
        super(width, height, xOffset, yOffset, parent);
    }
}
