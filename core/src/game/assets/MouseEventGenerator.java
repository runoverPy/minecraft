package game.assets;

import game.assets.ui_elements.MouseEvent;
import game.window.GLFWWindow;
import game.main.Main;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class MouseEventGenerator {
    private boolean mousePressed;

    public MouseEventGenerator() {
        mousePressed = isClicking();
    }

    protected final boolean isClicking() {
        return Main.getActiveWindow().getMouseButton(GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS;
    }

    public MouseEvent update() {
        GLFWWindow.Position<Double> cursorPos = Main.getActiveWindow().getCursorPos();
        boolean oldMousePressed = mousePressed;
        mousePressed = isClicking();
        if (oldMousePressed && !mousePressed) return new MouseEvent(MouseEvent.EventType.RELEASED, cursorPos.x(), cursorPos.y());
        if (!oldMousePressed && mousePressed) return new MouseEvent(MouseEvent.EventType.PRESSED, cursorPos.x(), cursorPos.y());
        return null;
    }
}
