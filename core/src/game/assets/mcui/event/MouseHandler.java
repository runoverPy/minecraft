package game.assets.mcui.event;

import game.core.GLFWWindow;
import game.assets.mcui.ContentRoot;
import game.main.Main;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseHandler {
    private final ContentRoot contentRoot;

    public MouseHandler(ContentRoot contentRoot) {
        this.contentRoot = contentRoot;
    }

    private EventTarget currentEventTarget = null;
    private MouseEvent lastEvent = null;
    private boolean hover = false;

    public void handleMouseClickEvent(int button, int action, int mods) {
        EventType<MouseEvent> eventType;
        if (action == GLFW_PRESS) {
            eventType = MouseEvent.MOUSE_PRESSED;
        } else if (action == GLFW_RELEASE) {
            eventType = MouseEvent.MOUSE_RELEASED;
        } else return;

        MouseEvent mouseEvent = new MouseEvent(
          eventType, button, mods
        );

        GLFWWindow.Position<Double> cursorPosition = Main.getActiveWindow().getCursorPos();
        EventTarget eventTarget = contentRoot.pick(cursorPosition.getX(), cursorPosition.getY());

        process(mouseEvent, eventTarget);
    }

    public void handleMouseEnterEvent() {

    }

    private void process(MouseEvent event, EventTarget target) {

    }
}
