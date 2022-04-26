package game.assets.widgets;

import game.assets.boxes.Box;
import game.util.buffer.BoolBuffer;
import org.joml.Matrix4f;
import org.joml.Vector4f;

class SwitchButton extends AbstractButton {
    private final String name;
    private final BoolBuffer buffer;

    public SwitchButton(int width, int height, int xOffset, int yOffset, Box parent, String name, BoolBuffer buffer) {
        super(width, height, xOffset, yOffset, parent);
        this.name = name;
        this.buffer = buffer;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrixPV) {
        Vector4f frameColor = isHovering(pxScale) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);

        ColorBox buttonFrame = new ColorBox(getWidth(), getHeight(), getXOffset(), getYOffset(), getParent(), frameColor);
        buttonFrame.draw(pxScale, matrixPV);
        ImageBox buttonImage = new ImageBox(getWidth() - 2, getHeight() - 2, 1, 1, this, filler);
        buttonImage.draw(pxScale, matrixPV);
        TextBox description = new TextBox(getWidth() - 2, getHeight() - 2, 1, 1, this, name + ": " + buffer.getValue(), true, true);
        description.draw(pxScale, matrixPV);

        update(pxScale);
        if (released()) {
            System.out.println(name);
            buffer.setValue(!buffer.getValue());
        }
    }
}
