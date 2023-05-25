package game.assets.event;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_MOD_NUM_LOCK;

public class KeyEvent extends Event {
    public static final EventType<KeyEvent> ANY =
            new EventType<>(Event.ANY, "KEY");
    public static final EventType<KeyEvent> KEY_PRESSED =
            new EventType<>(ANY, "KEY_PRESSED");
    public static final EventType<KeyEvent> KEY_RELEASED =
            new EventType<>(ANY, "KEY_RELEASED");
    public static final EventType<KeyEvent> KEY_REPEAT =
            new EventType<>(ANY, "KEY_REPEAT");

    public final int key, scancode, mods;

    public KeyEvent(EventType<KeyEvent> eventType, int key, int scancode, int mods) {
        super(eventType);
        this.key = key;
        this.scancode = scancode;
        this.mods = mods;
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
