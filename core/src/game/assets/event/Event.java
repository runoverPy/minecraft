package game.assets.event;

public abstract class Event {
    public static final EventType<Event> ANY = EventType.ROOT;

    private boolean captured;
    private EventType<? extends Event> eventType;

    public Event(EventType<? extends Event> eventType) {
        this.eventType = eventType;
    }

    public void capture() {
        captured = true;
    }

    public boolean isCaptured() {
        return captured;
    }

    public EventType<? extends Event> getEventType() {
        return eventType;
    }

}
