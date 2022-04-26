package game.assets.widgets;

import game.assets.boxes.Box;
import game.main.Main;

import static game.assets.GLFWWindow.Position;

import static org.lwjgl.glfw.GLFW.*;

abstract class ClickFrame extends ChildBox {
    private boolean mousePressed;
    private boolean pressed;
    private boolean released;

    public ClickFrame(int width, int height, int xOffset, int yOffset, Box parent) {
        super(width, height, xOffset, yOffset, parent);
        mousePressed = isClicking();
        pressed = false;
        released = false;
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

    protected final boolean isClicking() {
        return Main.getActiveWindow().getMouseButton(GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS;
    }

    protected boolean released() {
        return released;
    }

    protected boolean pressed() {
        return pressed;
    }

    /**
     * fetches current mouse activity and caches results. Must be called before <li></li>
     * @param pxScale
     */
    public void update(int pxScale) {
        boolean oldMousePressed = mousePressed;
        mousePressed = isClicking();
        released = oldMousePressed && !mousePressed && isHovering(pxScale);
        pressed = !oldMousePressed && mousePressed && isHovering(pxScale);
    }
}
