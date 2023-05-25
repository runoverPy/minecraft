package game.assets.event;

import static org.lwjgl.glfw.GLFW.*;

public enum MouseButton {
    Left,
    Right,
    Middle,
    Mouse4,
    Mouse5,
    Mouse6,
    Mouse7,
    Mouse8;

    MouseButton getMouseButton(int button) {
        return switch (button) {
            case GLFW_MOUSE_BUTTON_LEFT -> Left;
            case GLFW_MOUSE_BUTTON_RIGHT -> Right;
            case GLFW_MOUSE_BUTTON_MIDDLE -> Middle;
            case GLFW_MOUSE_BUTTON_4 -> Mouse4;
            case GLFW_MOUSE_BUTTON_5 -> Mouse5;
            case GLFW_MOUSE_BUTTON_6 -> Mouse6;
            case GLFW_MOUSE_BUTTON_7 -> Mouse7;
            case GLFW_MOUSE_BUTTON_8 -> Mouse8;
            default -> throw new IllegalStateException("Unexpected value: " + button);
        };
    }
}
