package game.assets.event;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_MOD_NUM_LOCK;

public class MouseEvent extends Event {
    public static final EventType<MouseEvent> ANY =
            new EventType<>(Event.ANY, "MOUSE");
    public static final EventType<MouseEvent> MOUSE_PRESSED =
            new EventType<>(MouseEvent.ANY, "MOUSE_PRESSED");
    public static final EventType<MouseEvent> MOUSE_RELEASED =
            new EventType<>(MouseEvent.ANY, "MOUSE_RELEASED");
    public static final EventType<MouseEvent> MOUSE_CLICKED =
            new EventType<>(MouseEvent.ANY, "MOUSE_CLICKED");

    public static final EventType<MouseEvent> MOUSE_MOVED =
            new EventType<>(MouseEvent.ANY, "MOUSE_MOVED");
    public static final EventType<MouseEvent> MOUSE_ENTERED =
            new EventType<>(MouseEvent.ANY, "MOUSE_ENTERED");
    public static final EventType<MouseEvent> MOUSE_EXITED =
            new EventType<>(MouseEvent.ANY, "MOUSE_EXITED");

    private MouseButton button;
    private int mods;

    public MouseEvent(EventType<? extends MouseEvent> eventType, MouseButton button, int mods) {
        super(eventType);
        this.button = button;
        this.mods = mods;
    }
    
    public MouseButton getButton() {
        return button;
    }

    public boolean isShift() {
        return (mods & GLFW_MOD_SHIFT) != 0;
    }

    public boolean isControl() {
        return (mods & GLFW_MOD_CONTROL) != 0;
    }

    public boolean isAlt() {
        return (mods & GLFW_MOD_ALT) != 0;
    }

    public boolean isSuper() {
        return (mods & GLFW_MOD_SUPER) != 0;
    }

    public boolean isCapsLock() {
        return (mods & GLFW_MOD_CAPS_LOCK) != 0;
    }

    public boolean isNumLock() {
        return (mods & GLFW_MOD_NUM_LOCK) != 0;
    }
}
