package game.assets.ui_elements.control;

import game.assets.ui_elements.*;
import game.assets.ui_elements.asset.ColorBox;
import game.assets.ui_elements.asset.ImageBox;
import game.main.Main;
import game.util.Image;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class ScrollBarY extends ChildBox {
    private double value;
    private final Knob knob;

    public ScrollBarY(int height, int xOffset, int yOffset, Component parent) {
        super(5, height, xOffset, yOffset, parent);
        this.knob = new Knob(5, height / 10, xOffset, yOffset, parent);
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        ColorBox frame = new ColorBox(getWidth(), getHeight(), 0, 0, this, new Vector4f(0.375f));
        frame.draw(pxScale, matrix4f);
        knob.draw(pxScale, matrix4f);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        return knob.onMouseEvent(event, pxScale);
    }

    private class Knob extends ChildBox {
        private boolean clicked;
        private int clickOffset;

        public Knob(int width, int height, int xOffset, int yOffset, Component parent) {
            super(width, height, xOffset, yOffset, parent);
            clicked = false;
            clickOffset = 0;
        }

        @Override
        public void draw(int pxScale, Matrix4f matrix4f) {
            if (Main.getActiveWindow().getMouseButton(GLFW_MOUSE_BUTTON_LEFT) != GLFW_PRESS && clicked) {
                clicked = false;
            }

            if (clicked) {
                int mouseY = Main.getActiveWindow().getCursorPos().y().intValue();
                int knobPos = mouseY - clickOffset;
                int scrollLength = getParent().getHeight(pxScale) - getHeight(pxScale);
                int topEnd = getParent().getCornerY(pxScale), botEnd = topEnd + scrollLength;
                int framedLength = Math.max(topEnd, Math.min(knobPos, botEnd)) - topEnd;
                value = (double) framedLength / scrollLength;
            }

            Vector4f frameColor = clicked || hovering(pxScale) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);

            ColorBox outerBox = new ColorBox(getWidth(), getHeight(), 0, 0, this, frameColor);
            outerBox.draw(pxScale, matrix4f);
            ImageBox center = new ImageBox(getWidth() - 2, getHeight() - 2, 1, 1, this, Image.loadImage("/img/stone.png"));
            center.draw(pxScale, matrix4f);
        }

        @Override
        public int getCornerY(int pxScale) {
            return (int) (value * (getParent().getHeight(pxScale) - getHeight(pxScale))) + super.getCornerY(pxScale);
        }

        @Override
        public boolean onMouseEvent(MouseEvent event, int pxScale) {
            if (event.eventType == MouseEvent.EventType.PRESSED && contains(event, pxScale)) {
                clicked = true;
                clickOffset = (int) event.mouseY - getCornerY(pxScale);
                return true;
            }
            return false;
        }
    }
}
