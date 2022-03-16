package game.assets.menus;

import game.util.Fn;

public class PauseHandler extends MenuHandler {
    private boolean active;
    private final Fn onFreeze, onUnFreeze;

    public PauseHandler(Fn onFreeze, Fn onUnFreeze) {
        super();
        next(new PauseMenu(this));
        this.onFreeze = onFreeze;
        this.onUnFreeze = onUnFreeze;
    }

    @Override
    public void render() {
        if (active) super.render();
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
        onFreeze.call();
    }

    public void deactivate() {
        active = false;
        onUnFreeze.call();
    }
}
