package game.assets.mcui;

public enum ItemScale {
    SMALL (512),
    MEDIUM (384),
    LARGE (256),
    GIANT (128);

    private final int pixels;

    ItemScale(int pixels) {
        this.pixels = pixels;
    }

    public int getPixels() {
        return pixels;
    }
}
