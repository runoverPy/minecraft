package game.assets.event.dispatch;

import game.assets.event.Event;
import game.assets.event.EventTarget;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * todo implement event dispatch to multiple targets.
 * Implement in such a way so dispatches for one and dispatches for multiple targets go through the same pipeline
 */
public class EventLauncher {
    private static final Queue<EventDispatchChain> dispatchChains
            = new ArrayDeque<>();

    public static Event fireEvent(EventTarget target, Event event) {
        EventDispatchChain dispatchChain;
        synchronized (dispatchChains) {
            dispatchChain = dispatchChains.poll();
        }
        if (dispatchChain == null)
            dispatchChain = new LinkedEventDispatchChain();
        Event result = fireEvent(dispatchChain, target, event);
        dispatchChain.reset();
        synchronized (dispatchChains) {
            dispatchChains.add(dispatchChain);
        }
        return result;
    }

    public static Event fireEvent(Event event, EventTarget... eventTargets) {
        return null; // TODO: 20.05.23 implement this
    }

    private static Event fireEvent(EventDispatchChain dispatchChain, EventTarget eventTarget, Event event) {
        dispatchChain = eventTarget.buildEventDispatchChain(dispatchChain);
        return dispatchChain.dispatchEvent(event);
    }

    public class EventQueue {
        private final Queue<EventRecord> eventQueue;

        private EventQueue() {
            this.eventQueue = new ArrayDeque<>();
        }

        public void fireEvents() {
            // todo fires all events in this queue on this thread
        }
    }

    private record EventRecord (EventTarget target, Event event) {}
}
