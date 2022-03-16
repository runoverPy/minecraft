package game.assets.widgets;

import game.main.Main;

import static game.main.GLFWWindow.Position;

import static org.lwjgl.glfw.GLFW.*;

public abstract class ClickFrame extends ChildBox {
    private boolean mousePressed;

    public ClickFrame(int width, int height, int xOffset, int yOffset, Box parent) {
        super(width, height, xOffset, yOffset, parent);
    }

    protected final boolean isHovering(int pxScale) {
        Position<Double> cursorPos = Main.getActiveWindow().getCursorPos();
        
        int
                left = getCornerX(pxScale),
                top = getCornerY(pxScale),
                right = left + getWidth(pxScale),
                bottom = top + getHeight(pxScale);

        return left < cursorPos.getX() && cursorPos.getX() < right && top < cursorPos.getY() && cursorPos.getY() < bottom;
    }

    protected final boolean isPressing() {
        return Main.getActiveWindow().getMouseButton(GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS;
    }

    protected boolean released() {
        boolean oldMousePressed = mousePressed;
        mousePressed = isPressing();
        return oldMousePressed && !mousePressed;
    }

    protected void onPress() {}

    protected void onRelease() {}

    protected boolean clicked(int pxScale) {
        return released() && isHovering(pxScale);
    }
}
