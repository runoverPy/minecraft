package game.assets.ui_elements;

import game.window.GLFWWindow;
import game.main.Main;
import org.joml.Matrix4f;

public abstract class ChildBox implements Component {
    private Component parent;
    private final int width;
    private final int height;
    private int xOffset;
    private int yOffset;

    public ChildBox(int width, int height, int xOffset, int yOffset, Component parent) {
        this.parent = parent;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void setParent(Component newParent) {
        this.parent = newParent;
    }
    @Override
    public int getCornerX(int pxScale) {
        return getXOffset(pxScale) + parent.getCornerX(pxScale);
    }

    @Override
    public int getCornerY(int pxScale) {
        return getYOffset(pxScale) + parent.getCornerY(pxScale);
    }

    public int getPixelScale() {
        return 1;
    }

    public Component getParent() {
        return parent;
    };

    public abstract void draw(int pxScale, Matrix4f matrix4f);

    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        return false;
    }

    @Override
    public int getWidth() {
        return this.width > 0 ? this.width : getParent().getWidth() + this.width;
    }

    @Override
    public int getHeight() {
        return this.height > 0 ? this.height : getParent().getHeight() + this.height;
    }

    @Override
    public int getWidth(int pxScale) {
        return getWidth() * pxScale;
    }

    @Override
    public int getHeight(int pxScale) {
        return getHeight() * pxScale;
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

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    protected final boolean contains(MouseEvent event, int pxScale) {
        return contains(event.mouseX, event.mouseY, pxScale);
    }

    public final boolean contains(double x, double y, int pxScale) {
        int
          top = getCornerY(pxScale),
          left = getCornerX(pxScale),
          right = left + getWidth(pxScale),
          bottom = top + getHeight(pxScale);

        return left < x && x < right && top < y && y < bottom;
    }

    protected boolean hovering(int x, int y, int pxScale) {
        return false;
    }

    protected final boolean hovering(int pxScale) {
        GLFWWindow.Position<Double> cursorPos = Main.getActiveWindow().getCursorPos();
        return contains(cursorPos.x(), cursorPos.y(), pxScale);
    }

    @Override
    public Component getTopComponent(int x, int y, int pxScale) {
        return contains(x, y, pxScale) ? this : null;
    }
}
