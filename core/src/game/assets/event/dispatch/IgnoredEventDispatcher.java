package game.assets.event.dispatch;

import game.assets.event.Event;

public final class IgnoredEventDispatcher implements EventDispatcher {
    @Override
    public Event dispatchEvent(Event event, EventDispatchChain chain) {
        return chain.dispatchEvent(event);
    }

    @Override
    public Event dispatchCapturingEvent(Event event) {
        return event;
    }

    @Override
    public Event dispatchBubblingEvent(Event event) {
        return event;
    }
}
