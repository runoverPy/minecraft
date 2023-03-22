package game.assets;

import game.main.Main;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class Callback {
    private final int key;
    private final Runnable callback;
    private boolean keyPressed;

    public Callback(int glfwKey, Runnable callback) {
        this.key = glfwKey;
        this.callback = callback;
    }

    public boolean check() {
        boolean newPressed = Main.getActiveWindow().getKey(key) == GLFW_PRESS;
        boolean out = false;
        if (keyPressed && !newPressed) {
            callback.run();
            out = true;
        }
        keyPressed = newPressed;
        return out;
    }
}
