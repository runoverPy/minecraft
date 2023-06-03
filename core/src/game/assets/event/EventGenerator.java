package game.assets.event;

import game.assets.event.dispatch.EventLauncher;
import game.assets.mcui.ContentRoot;
import game.window.*;
import game.main.Main;

import static org.lwjgl.glfw.GLFW.*;

public class EventGenerator {
    private final ContentRoot contentRoot;

    private final KeyCallback keyCallback = this::launchKeyEvent;
    private final CharCallback charCallback = this::launchCharEvent;
    private final MouseButtonCallback mouseButtonCallback = this::launchMouseEvent;
    private final ScrollCallback scrollCallback = this::launchScrollEvent;
    private final CursorPosCallback cursorPosCallback = this::launchCursorPosEvent;

    private double lastCursorPosX, lastCursorPosY;
    private EventTarget lastHovered;

    public EventGenerator(ContentRoot contentRoot) {
        this.contentRoot = contentRoot;
    }

    private void launchKeyEvent(int key, int scancode, int action, int mods) {
        EventType<KeyEvent> eventType = switch (action) {
            case GLFW_RELEASE -> KeyEvent.KEY_RELEASED;
            case GLFW_PRESS -> KeyEvent.KEY_PRESSED;
            case GLFW_REPEAT -> KeyEvent.KEY_REPEAT;
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
        KeyEvent event = new KeyEvent(eventType, key, scancode, mods);
        EventTarget eventTarget = contentRoot.getFocusedElement(); // eventTarget is focused component, gotten from contentRoot
        fireEvent(event, eventTarget);
    }

    private void launchCharEvent(int codepoint) {
        CharEvent charEvent = new CharEvent((char) codepoint);
        EventTarget eventTarget = contentRoot.getFocusedElement();
        fireEvent(charEvent, eventTarget);
    }

    private void launchMouseEvent(int button, int action, int mods) {
        EventType<MouseEvent> eventType = switch (action) {
            case GLFW_RELEASE -> MouseEvent.MOUSE_RELEASED;
            case GLFW_PRESS -> MouseEvent.MOUSE_PRESSED;
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
        MouseButton mouseButton = switch (button) {
            case GLFW_MOUSE_BUTTON_LEFT -> MouseButton.Left;
            case GLFW_MOUSE_BUTTON_RIGHT -> MouseButton.Right;
            case GLFW_MOUSE_BUTTON_MIDDLE -> MouseButton.Middle;
            case GLFW_MOUSE_BUTTON_4 -> MouseButton.Mouse4;
            case GLFW_MOUSE_BUTTON_5 -> MouseButton.Mouse5;
            case GLFW_MOUSE_BUTTON_6 -> MouseButton.Mouse6;
            case GLFW_MOUSE_BUTTON_7 -> MouseButton.Mouse7;
            case GLFW_MOUSE_BUTTON_8 -> MouseButton.Mouse8;
            default -> throw new IllegalStateException("Unexpected value: " + button);
        };
        MouseEvent mouseEvent = new MouseEvent(eventType, mouseButton, mods);
        GLFWWindow.Position<Double> cursorPosition = Main.getActiveWindow().getCursorPos();
        EventTarget eventTarget = contentRoot.pick(cursorPosition.x(), cursorPosition.y());
        if (eventType == MouseEvent.MOUSE_PRESSED) System.out.println(eventTarget);
        fireEvent(mouseEvent, eventTarget);
    }

    private void launchScrollEvent(double xOffset, double yOffset) {
        ScrollEvent scrollEvent = new ScrollEvent(xOffset, yOffset);
        GLFWWindow.Position<Double> cursorPosition = Main.getActiveWindow().getCursorPos();
        EventTarget eventTarget = contentRoot.pick(cursorPosition.x(), cursorPosition.y());
        fireEvent(scrollEvent, eventTarget);
    }

    private void launchCursorPosEvent(double cursorPosX, double cursorPosY) {
        double deltaX = cursorPosX - lastCursorPosX, deltaY = cursorPosY - lastCursorPosY;
        // todo compare past and current eventTarget trees, firing MouseEvent.ENTERED/EXITED as needed

    }

    public void attachCallbacks(GLFWWindow glfwWindow) {
        glfwWindow.addKeyCallback(keyCallback);
        glfwWindow.addCharCallback(charCallback);
        glfwWindow.addMouseButtonCallback(mouseButtonCallback);
        glfwWindow.addScrollCallback(scrollCallback);
        glfwWindow.addCursorPosCallback(cursorPosCallback);
    }

    public void detachCallbacks(GLFWWindow glfwWindow) {
        glfwWindow.delKeyCallback(keyCallback);
        glfwWindow.delCharCallback(charCallback);
        glfwWindow.delMouseButtonCallback(mouseButtonCallback);
        glfwWindow.delScrollCallback(scrollCallback);
        glfwWindow.delCursorPosCallback(cursorPosCallback);
    }

    private void fireEvent(Event event, EventTarget target) {
        if (target != null && event != null)
            EventLauncher.fireEvent(target, event);
    }
}
