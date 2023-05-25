package game.assets.event.dispatch;

import game.assets.event.Event;

public interface EventDispatcher {
    Event dispatchEvent(Event event, EventDispatchChain chain);

    Event dispatchCapturingEvent(Event event);

    Event dispatchBubblingEvent(Event event);

    default String toTreeString() {
        return toString();
    }
}
