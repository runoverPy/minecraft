package game.assets.event.dispatch;

import game.assets.event.Event;

public interface EventDispatchChain {
    EventDispatchChain append(EventDispatcher chain);
    EventDispatchChain prepend(EventDispatcher chain);
    Event dispatchEvent(Event event);
    void reset();
}
