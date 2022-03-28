package game.assets.text;

public class GlyphMap {
    protected static float[] getGlyphUV(int index, int countX, int countY) {
        return new float[] {
                (index % countX) * 1f / countX,
                1 - (index / countY) * 1f / countY
        };
    }

    protected static float[] getCornerUV(int corner, int countX, int countY) {
        return new float[] {
                (corner % 2) * 1f / countX,
                1 - (corner / 2) * 1f / countY
        };
    }

    protected static int getIndex(char c) {
        if (c < ' ') {
            c = (char) 127;
        }
        if (c > (char) 127) c = (char) 127;
        return (int) c - 32;
    }
}
