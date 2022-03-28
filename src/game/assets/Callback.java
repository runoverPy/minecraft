package game.assets;

import game.main.Main;
import game.util.Fn;

import static org.lwjgl.glfw.GLFW.*;

public class Callback {
    private final int key;
    private final Fn callback;
    private boolean keyPressed;

    public Callback(int glfwKey, Fn callback) {
        this.key = glfwKey;
        this.callback = callback;
    }

    public boolean check() {
        boolean newPressed = Main.getActiveWindow().getKey(key) == GLFW_PRESS;
        boolean out = false;
        if (keyPressed && !newPressed) {
            callback.call();
            out = true;
        }
        keyPressed = newPressed;
        return out;
    }

    public void invoke() {
        callback.call();
    }
}
