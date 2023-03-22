package game.assets.mcui.event;

public class KeyEvent extends Event {
    public final int key, scancode, action, mods;

    public KeyEvent(int key, int scancode, int action, int mods) {
        super(EventType.ROOT);
        this.key = key;
        this.scancode = scancode;
        this.action = action;
        this.mods = mods;
    }
}
