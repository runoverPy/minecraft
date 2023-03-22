package game.assets.mcui.event;

import org.lwjgl.glfw.*;

public class EventGenerator {
    private final GLFWKeyCallbackI keyCallback;
    private final GLFWCharCallbackI charCallback;
    private final GLFWMouseButtonCallbackI mouseButtonCallback;
    private final GLFWCursorPosCallbackI cursorPosCallback;


    public EventGenerator() {
        keyCallback = (window, key, scancode, action, mods) -> {};
        charCallback = (window, codepoint) -> {};
        mouseButtonCallback = (window, button, action, mods) -> {};
        cursorPosCallback = (window, xpos, ypos) -> {};
    }
}
