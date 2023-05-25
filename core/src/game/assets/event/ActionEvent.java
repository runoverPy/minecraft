package game.assets.event;

public class ActionEvent extends Event {
    public static final EventType<ActionEvent> ACTION
            = new EventType<>(ANY, "ACTION");

    public ActionEvent() {
        super(ACTION);
    }
}
