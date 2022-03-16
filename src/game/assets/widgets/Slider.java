package game.assets.widgets;

import game.main.Main;
import game.util.Image;
import game.util.buffer.FloatBuffer;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class Slider extends ChildBox {
    private final FloatBuffer value;

    private final ChildBox scrollRail;
    private final Knob knob;

    public Slider(int width, int height, int xOffset, int yOffset, Box parent, FloatBuffer value) {
        super(width, height, xOffset, yOffset, parent);
        this.value = value;
        scrollRail = new ColorBox(width - 2, height - 2, 1, 1, this, new Vector4f(0.75f, 0.75f, 0.75f, 1f));
        knob = new Knob(8, height, 0, 0, this);
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        ColorBox frame = new ColorBox(width, height, 0, 0, this, new Vector4f(0.375f, 0.375f, 0.375f, 1f));
        frame.draw(pxScale, matrix4f);

        TextBox description = new TextBox(width - 2, height - 2, 1, 1, this, Float.toString(value.getValue()), true, true);

        scrollRail.draw(pxScale, matrix4f);
        knob.draw(pxScale, matrix4f);
        description.draw(pxScale, matrix4f);
    }

    public float getValue() {
        return value.getValue();
    }

    private class Knob extends ClickFrame {
        private boolean clicked;
        private int clickOffset;

        public Knob(int width, int height, int xOffset, int yOffset, Box parent) {
            super(width, height, xOffset, yOffset, parent);
            clicked = false;
            clickOffset = 0;
        }

        @Override
        public int getCornerX(int pxScale) {
            return (int) (getValue() * (getParent().getWidth(pxScale) - getWidth(pxScale))) + super.getCornerX(pxScale);
        }

        @Override
        public void draw(int pxScale, Matrix4f matrix4f) {
            int mouseX = Main.getActiveWindow().getCursorPos().getX().intValue();

            if (clicked(pxScale) && !clicked) {
                clicked = true;
                clickOffset = mouseX - getCornerX(pxScale);
            }
            if (clicked) {
                int knobPos = mouseX - clickOffset;
                int scrollLength = getParent().getWidth(pxScale) - getWidth(pxScale);
                int leftEnd = getParent().getCornerX(pxScale), rightEnd = leftEnd + scrollLength;
                int framedLength = Math.max(leftEnd, Math.min(knobPos, rightEnd)) - leftEnd;
                value.setValue((float) framedLength / scrollLength);
            }
            if (!isPressing() && clicked) {
                clicked = false;
            }

            Vector4f frameColor = isHovering(pxScale) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);

            ColorBox outerBox = new ColorBox(width, height, 0, 0, this, frameColor);
            outerBox.draw(pxScale, matrix4f);
            ImageBox center = new ImageBox(width - 2, height - 2, 1, 1, this, Image.loadImage("/img/stone.png"));
            center.draw(pxScale, matrix4f);

        }

        @Override
        protected boolean clicked(int pxScale) {
            return Main.getActiveWindow().getMouseButton(GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS && isHovering(pxScale);
        }
    }
}
