package game.assets.ui_elements.control;

import game.assets.ui_elements.*;
import game.assets.ui_elements.asset.ColorBox;
import game.assets.ui_elements.asset.ImageBox;
import game.assets.ui_elements.asset.TextBox;
import game.util.Image;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.function.Supplier;

public abstract class AbstractButton extends ChildBox {
    private final Supplier<Boolean> isActive;
    private final ColorBox buttonCover;
    private final ColorBox buttonFrame;
    private final ImageBox buttonImage;
    private final TextBox description;

    protected static final Image filler = Image.loadImage("/img/stone.png");

    public AbstractButton(int width, int height, int xOffset, int yOffset, Component parent, String name, Supplier<Boolean> buffer) {
        super(width, height, xOffset, yOffset, parent);
        buttonCover = new ColorBox(getWidth(), getHeight(), 0, 0, this, new Vector4f(0, 0, 0, 0.5f));
        buttonFrame = new ColorBox(getWidth(), getHeight(), 0, 0, this, new Vector4f());
        buttonImage = new ImageBox(getWidth() - 2, getHeight() - 2, 1, 1, this, filler);
        description = new TextBox(getWidth() - 2, getHeight() - 2, 1, 1, this, name, true, true);
        this.isActive = buffer;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        if (isActive()) {
            Vector4f frameColor = hovering(pxScale) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);
            buttonFrame.setBoxColor(frameColor);
        }
        buttonFrame.draw(pxScale, matrix4f);
        buttonImage.draw(pxScale, matrix4f);
        description.draw(pxScale, matrix4f);
        if (!isActive()) {
            buttonCover.draw(pxScale, matrix4f);
        }
    }

    public boolean isActive() {
        return isActive.get();
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        if (event.eventType == MouseEvent.EventType.RELEASED && contains(event, pxScale) && isActive()) {
            onAction();
            return true;
        }
        return false;
    }

    public abstract void onAction();
}
