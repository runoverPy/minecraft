package game.assets.widgets;

import game.assets.boxes.Box;
import org.joml.Matrix4f;
import org.joml.Vector4f;

class Button extends AbstractButton {
    private final String name;
    private final Runnable callback;

    /**
     * creates a button centered on a given point
     * width and height will be determined dynamically
     *
     * @param name the text to be displayed on the button
     * @param callback the function to be executed on click
     */

    public Button(int width, int height, int xOffset, int yOffset, Box parent, String name, Runnable callback) {
        super(width, height, xOffset, yOffset, parent);
        this.name = name;
        this.callback = callback;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrixPV) {
        Vector4f frameColor = isHovering(pxScale) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);

        ColorBox buttonFrame = new ColorBox(getWidth(), getHeight(), getXOffset(), getYOffset(), getParent(), frameColor);
        buttonFrame.draw(pxScale, matrixPV);
        ImageBox buttonImage = new ImageBox(getWidth() - 2, getHeight() - 2, 1, 1, this, filler);
        buttonImage.draw(pxScale, matrixPV);
        TextBox description = new TextBox(getWidth() - 2, getHeight() - 2, 1, 1, this, name, true, true);
        description.draw(pxScale, matrixPV);

        update(pxScale);
        if (pressed()) {
            callback.run();
        }
    }
}