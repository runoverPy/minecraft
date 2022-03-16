package game.assets.widgets;

public enum ElementSizes {
    XS (1),
    S (2),
    M (3),
    L (4);

    private final int aspectRatio;

    ElementSizes(int aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }
}
