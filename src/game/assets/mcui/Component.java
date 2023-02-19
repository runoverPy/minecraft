package game.assets.mcui;

import game.assets.mcui.event.EventTarget;
import org.joml.Matrix4f;

public abstract class Component implements EventTarget {
    private int width, height;
    private boolean resizeable = true;

    public Component() {
        width = minWidth();
        height = minHeight();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public final boolean setWidth(int width) {
        return setDimensions(width, getHeight());
    }

    public final boolean setHeight(int height) {
        return setDimensions(getWidth(), height);
    }

    public final boolean setDimensions(int width, int height) {
        if (!isResizeable()) return false;
        if (width < minWidth() || maxWidth() < width) throw new IllegalArgumentException("Illegal width value");
        if (height < minHeight() || maxHeight() < height) throw new IllegalArgumentException("Illegal height value");
        this.width = width;
        this.height = height;
        onResize(width, height);
        return true;
    }

    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
    }

    public boolean isResizeable() {
        return resizeable;
    }

    public abstract void render(Matrix4f matrix4f, int cornerX, int cornerY);

    protected void onResize(int newWidth, int newHeight) {}
    protected void onMove(int newX, int newY) {}


    protected int minWidth() {
        return 0;
    }

    protected int maxWidth() {
        return Integer.MAX_VALUE;
    }

    protected int minHeight() {
        return 0;
    }

    protected int maxHeight() {
        return Integer.MAX_VALUE;
    }

    public boolean contains(double x, double y) {
//        int
//          top = getCornerY(pxScale),
//          left = getCornerX(pxScale),
//          right = left + getWidth(),
//          bottom = top + getHeight();
//
//        return left < x && x < right && top < y && y < bottom;
        return false;
    }

    public Component pick(double x, double y) {
        return this;
    }
}
