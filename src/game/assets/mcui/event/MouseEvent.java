package game.assets.mcui.event;

public class MouseEvent extends Event {
    public static final EventType<MouseEvent> ANY =
      new EventType<>(Event.ANY, "MOUSE");
    public static final EventType<MouseEvent> MOUSE_PRESSED =
      new EventType<>(MouseEvent.ANY, "MOUSE_PRESSED");
    public static final EventType<MouseEvent> MOUSE_RELEASED =
      new EventType<>(MouseEvent.ANY, "MOUSE_RELEASED");

    private int button, mods;

    public MouseEvent(EventType<? extends MouseEvent> eventType, int button, int mods) {
        super(eventType);
        this.button = button;
        this.mods = mods;
    }
}
