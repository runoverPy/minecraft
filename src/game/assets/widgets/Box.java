package game.assets.widgets;

abstract class Box {
    protected final int width, height, xOffset, yOffset;

    public Box(int width, int height, int xOffset, int yOffset) {
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    abstract public int getCornerX(int pxScale);

    public abstract int getCornerY(int pxScale);

    public int getCenterX(int pxScale) {
        return getCornerX(pxScale) + width * pxScale / 2;
    }

    public int getCenterY(int pxScale) {
        return getCornerY(pxScale) + height * pxScale / 2;
    }

    public int getWidth(int pxScale) {
        return width * pxScale;
    }

    public int getHeight(int pxScale) {
        return height * pxScale;
    }

    public int getWidth() {
        return getWidth(1);
    }

    public int getHeight() {
        return getHeight(1);
    }

    public abstract int getPixelScale();
}
 