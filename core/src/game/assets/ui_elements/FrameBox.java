package game.assets.ui_elements;

import org.joml.Matrix4f;
import org.joml.Vector4f;

public class FrameBox extends ChildBox {
    private final ColorBox[] frameParts;
    private Vector4f frameColor;

    public FrameBox(int width, int height, int xOffset, int yOffset, Component parent, Vector4f color, int thickness) {
        super(width, height, xOffset, yOffset, parent);
        this.frameParts = new ColorBox[] {
          new ColorBox(width, thickness, 0, 0, this, color),
          new ColorBox(thickness, height - thickness, 0, thickness, this, color),
          new ColorBox(thickness, height - thickness, width - thickness, thickness, this, color),
          new ColorBox(width - 2 * thickness, thickness, thickness, height - thickness, this, color)
        };
        this.frameColor = color;
    }

    public Vector4f getFrameColor() {
        return frameColor;
    }

    public void setFrameColor(Vector4f frameColor) {
        this.frameColor = frameColor;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        for (ColorBox framePart : frameParts) framePart.draw(pxScale, matrix4f);
    }
}
