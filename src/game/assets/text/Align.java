package game.assets.text;

public enum Align {
    CENTER (-0.5f, -0.5f),
    LEFT (-1f, 0f),
    RIGHT (0f, 0f),
    TOP (0f, -1f),
    BOTTOM (0f, 0f),
    BOTTOM_RIGHT;

    private final float xOffset, yOffset;

    Align() {
        this(0, 0);
    }

    Align(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }
}
