package game.mechanics.input;

import game.main.Main;

import static org.lwjgl.glfw.GLFW.*;

public class CooldownMouseInput {
    private final int button;

    private final long cooldown;
    private final boolean spam;

    private boolean wasTriggered;
    private long lastTrigger;

    public CooldownMouseInput(int button, long cooldown, boolean spam) {
        this.button = button;
        this.cooldown = cooldown;
        this.spam = spam;
    }

    public CooldownMouseInput(int button, long cooldown) {
        this(button, cooldown, false);
    }
    
    public boolean check() {
        boolean out = false;
        boolean isTriggered = Main.getActiveWindow().getMouseButton(button) == GLFW_PRESS;
        long now = System.currentTimeMillis();
        if ((now - lastTrigger >= cooldown || (spam && !wasTriggered)) && isTriggered) {
            lastTrigger = now;
            out = true;
        }
        wasTriggered = isTriggered;
        return out;
    }
}
