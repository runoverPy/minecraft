package game.assets.event.dispatch;

import game.assets.event.Event;
import game.assets.event.EventProcessor;

import java.util.ArrayList;
import java.util.List;

public class MultipleEventProcessor<T extends Event> {
    private final List<EventProcessor<T>> eventFilters, eventHandlers;

    public MultipleEventProcessor() {
        eventFilters = new ArrayList<>();
        eventHandlers = new ArrayList<>();
    }

    void addEventFilter(EventProcessor<T> eventFilter) {
        if (!eventFilters.contains(eventFilter))
            eventFilters.add(eventFilter);
    }

    void addEventHandler(EventProcessor<T> eventHandler) {
        if (!eventHandlers.contains(eventHandler))
            eventHandlers.add(eventHandler);
    }

    void removeEventFilter(EventProcessor<?> eventFilter) {
        eventFilters.remove(eventFilter);
    }

    void removeEventHandler(EventProcessor<?> eventHandler) {
        eventHandlers.remove(eventHandler);
    }

    public void dispatchCapturingEvent(Event event) {
        T specificEvent = (T) event;
        for (EventProcessor<T> eventFilter : eventFilters) {
            eventFilter.process(specificEvent);
        }
    }

    public void dispatchBubblingEvent(Event event) {
        T specificEvent = (T) event;

        for (EventProcessor<T> eventHandler : eventHandlers) {
            eventHandler.process(specificEvent);
        }
    }
}
