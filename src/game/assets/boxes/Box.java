package game.assets.boxes;

public abstract class Box {
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

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

    public int getXOffset(int pxScale) {
        return xOffset * pxScale;
    }

    public int getYOffset(int pxScale) {
        return yOffset * pxScale;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public abstract int getPixelScale();
}
 