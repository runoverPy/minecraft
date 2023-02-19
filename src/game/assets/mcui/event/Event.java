package game.assets.mcui.event;

public abstract class Event {
    public static final EventType<Event> ANY = EventType.ROOT;

    private EventType<? extends Event> eventType;

    public Event(EventType<? extends Event> eventType) {
        this.eventType = eventType;
    }

    public void capture() {

    }
}
