package game.assets.event;

import game.assets.event.dispatch.EventDispatchChain;
import game.assets.event.dispatch.EventLauncher;

public interface EventTarget {
    EventDispatchChain buildEventDispatchChain(EventDispatchChain chain);

    default Event fireEvent(Event event) {
        return EventLauncher.fireEvent(this, event);
    }
}
