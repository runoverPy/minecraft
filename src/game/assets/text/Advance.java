package game.assets.text;

public enum Advance {
    UP_LEFT(-1, 1),
    UP_CENTER (-0.5f, 1),
    UP_RIGHT(0, 1),
    DOWN_LEFT(-1, -1),
    DOWN_CENTER (-0.5f, -1),
    DOWN_RIGHT( 0, -1);

    private final float xAdvance, yAdvance;

    Advance(float xAdvance, float yAdvance) {
        this.xAdvance = xAdvance;
        this.yAdvance = yAdvance;
    }

    public float getXAdvance() {
        return xAdvance;
    }

    public float getYAdvance() {
        return yAdvance;
    }
}
