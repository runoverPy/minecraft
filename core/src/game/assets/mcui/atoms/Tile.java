package game.assets.mcui.atoms;

import game.util.Util;

public final class Tile {
    private static final float[] tileVertices = new float[]{
            0, 0, -1, 0, 0,
            0, -1, -1, 0, 1,
            1, -1, -1, 1, 1,
            1, 0, -1, 1, 0,
    };
    public static final int vertices;

    static {
        vertices = Util.genArrayBuffer(tileVertices);
    }
}
