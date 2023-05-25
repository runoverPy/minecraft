package game.mechanics.input;

import game.main.Main;

import static org.lwjgl.glfw.GLFW.*;

public class CooldownKeyInput {
    private final int key;

    private final long cooldown;
    private final boolean spam;

    private boolean wasTriggered;
    private long lastTrigger;

    public CooldownKeyInput(int key, long cooldown, boolean spam) {
        this.key = key;
        this.cooldown = cooldown;
        this.spam = spam;
    }

    public CooldownKeyInput(int key, long cooldown) {
        this(key, cooldown, false);
    }

    public boolean check() {
        boolean out = false;
        boolean isTriggered = Main.getActiveWindow().getKey(key) == GLFW_PRESS;
        long now = System.currentTimeMillis();
        if ((now - lastTrigger >= cooldown || (spam && !wasTriggered)) && isTriggered) {
            lastTrigger = now;
            out = true;
        }
        wasTriggered = isTriggered;
        return out;
    }
}
