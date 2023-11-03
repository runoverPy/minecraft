package game.assets.menus;

import game.main.Main;

import static org.lwjgl.glfw.GLFW.*;

public class PauseHandler extends MenuHandler {
    private final Runnable onFreeze, onUnFreeze;

    public PauseHandler(Runnable onFreeze, Runnable onUnFreeze) {
        super(true);
        next(new PauseMenu(this));
        this.onFreeze = onFreeze;
        this.onUnFreeze = onUnFreeze;
    }

    @Override
    public void render() {
        if (isEnabled()) super.render();
    }

    @Override
    public void enable() {
        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        super.enable();
        onFreeze.run();
    }

    @Override
    public void disable() {
        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        super.disable();
        onUnFreeze.run();
    }

    @Override
    public void cleanup() {
        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        super.cleanup();
    }
}
