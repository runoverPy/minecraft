package game.assets.event.dispatch;

import game.assets.event.Event;

public class BasicEventDispatcher implements EventDispatcher {
    @Override
    public final Event dispatchEvent(Event event, EventDispatchChain chain) {
        event = dispatchCapturingEvent(event);
        if (event.isCaptured()) return event;
        event = chain.dispatchEvent(event);
        if (event.isCaptured()) return event;
        event = dispatchBubblingEvent(event);
        return event;
    }

    public Event dispatchCapturingEvent(Event event) {
        return event;
    }

    public Event dispatchBubblingEvent(Event event) {
        return event;
    }
}
