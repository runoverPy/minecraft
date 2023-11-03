package game.assets.event;

import game.assets.event.dispatch.EventLauncher;
import game.assets.mcui.Component;
import game.assets.mcui.ContentRoot;
import game.window.*;
import game.main.Main;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class EventGenerator {
    private ContentRoot target;

    private final KeyCallback keyCallback = this::launchKeyEvent;
    private final CharCallback charCallback = this::launchCharEvent;
    private final MouseButtonCallback mouseButtonCallback = this::launchMouseEvent;
    private final ScrollCallback scrollCallback = this::launchScrollEvent;
    private final CursorPosCallback cursorPosCallback = this::launchCursorPosEvent;

    private double lastCursorPosX, lastCursorPosY;
    private Component lastHovered = null;
    private List<EventTarget> lastHierarchy = new ArrayList<>();

    public EventGenerator(ContentRoot target) {
        this.target = target;
    }

    public EventGenerator() {
        this(null);
    }

    public ContentRoot getTarget() {
        return target;
    }

    public void setTarget(ContentRoot target) {
        this.target = target;
    }

    private void launchKeyEvent(int key, int scancode, int action, int mods) {
        if (target == null) return;
        EventType<KeyEvent> eventType = switch (action) {
            case GLFW_RELEASE -> KeyEvent.KEY_RELEASED;
            case GLFW_PRESS -> KeyEvent.KEY_PRESSED;
            case GLFW_REPEAT -> KeyEvent.KEY_REPEAT;
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
        KeyEvent event = new KeyEvent(eventType, key, scancode, mods);
        EventTarget eventTarget = target.getFocusedElement(); // eventTarget is focused component, gotten from contentRoot
        fireEvent(event, eventTarget);
    }

    private void launchCharEvent(int codepoint) {
        if (target == null) return;
        CharEvent charEvent = new CharEvent((char) codepoint);
        EventTarget eventTarget = target.getFocusedElement();
        fireEvent(charEvent, eventTarget);
    }

    private void launchMouseEvent(int button, int action, int mods) {
        if (target == null) return;
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
        EventTarget eventTarget = target.pick(cursorPosition.x(), cursorPosition.y());
        fireEvent(mouseEvent, eventTarget);
    }

    private void launchScrollEvent(double xOffset, double yOffset) {
        if (target == null) return;
        ScrollEvent scrollEvent = new ScrollEvent(xOffset, yOffset);
        GLFWWindow.Position<Double> cursorPosition = Main.getActiveWindow().getCursorPos();
        EventTarget eventTarget = target.pick(cursorPosition.x(), cursorPosition.y());
        fireEvent(scrollEvent, eventTarget);
    }

    private void launchCursorPosEvent(double cursorPosX, double cursorPosY) {
        if (target == null) return;
        double deltaX = cursorPosX - lastCursorPosX, deltaY = cursorPosY - lastCursorPosY;
        // todo compare past and current eventTarget trees, firing MouseEvent.ENTERED/EXITED as needed
        Component hovered = target.pick(cursorPosX, cursorPosY);
        // compare hierarchies
        Component pointing = hovered;
        List<EventTarget> currentHierarchy = new ArrayList<>();
        while (pointing != null) {
            currentHierarchy.add(pointing);
            pointing = pointing.getParent();
        }

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
