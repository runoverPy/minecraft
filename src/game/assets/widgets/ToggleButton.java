package game.assets.widgets;

import game.util.Cyclic;
import game.util.buffer.EnumBuffer;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class ToggleButton<E extends Enum<E> & Cyclic<E>> extends AbstractButton {
    private final String name;
    private final EnumBuffer<E> buffer;

    public ToggleButton(int width, int height, int xOffset, int yOffset, Box parent, String name, EnumBuffer<E> buffer) {
        super(width, height, xOffset, yOffset, parent);
        this.name = name;
        this.buffer = buffer;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrixPV) {
        Vector4f frameColor = isHovering(pxScale) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);

        ColorBox buttonFrame = new ColorBox(width, height, xOffset, yOffset, getParent(), frameColor);
        buttonFrame.draw(pxScale, matrixPV);
        ImageBox buttonImage = new ImageBox(width - 2, height - 2, 1, 1, this, filler);
        buttonImage.draw(pxScale, matrixPV);
        TextBox description = new TextBox(width - 2, height - 2, 1, 1, this, name + ": " + buffer.getValue(), true, true);
        description.draw(pxScale, matrixPV);

        if (clicked(pxScale)) {
            System.out.println(name);
            buffer.setValue(buffer.getValue().next());
        }
    }
}
