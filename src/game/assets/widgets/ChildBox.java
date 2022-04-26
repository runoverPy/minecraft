package game.assets.widgets;

import game.assets.boxes.Box;
import org.joml.Matrix4f;

public abstract class ChildBox extends Box {
    private final Box parent;

    public ChildBox(int width, int height, int xOffset, int yOffset, Box parent) {
        super(width, height, xOffset, yOffset);
        this.parent = parent;
    }

    @Override
    public int getCornerX(int pxScale) {
        return getXOffset(pxScale) + parent.getCornerX(pxScale);
    }

    @Override
    public int getCornerY(int pxScale) {
        return getYOffset(pxScale) + parent.getCornerY(pxScale);
    }

    @Override
    public final int getPixelScale() {
        return parent.getPixelScale();
    }

    public Box getParent() {
        return parent;
    };

    public abstract void draw(int pxScale, Matrix4f matrix4f);
}
