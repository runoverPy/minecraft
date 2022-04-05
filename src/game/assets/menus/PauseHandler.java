package game.assets.menus;

public class PauseHandler extends MenuHandler {
    private boolean active;
    private final Runnable onFreeze, onUnFreeze;

    public PauseHandler(Runnable onFreeze, Runnable onUnFreeze) {
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
        onFreeze.run();
    }

    public void deactivate() {
        active = false;
        onUnFreeze.run();
    }
}
