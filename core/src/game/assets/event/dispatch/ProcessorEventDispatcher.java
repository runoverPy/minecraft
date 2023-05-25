package game.assets.event.dispatch;

import game.assets.event.Event;
import game.assets.event.EventProcessor;
import game.assets.event.EventType;

import java.util.HashMap;
import java.util.Map;

public class ProcessorEventDispatcher extends BasicEventDispatcher {
    private final Map<EventType<? extends Event>, MultipleEventProcessor<? extends Event>> processors;

    public ProcessorEventDispatcher() {
        processors = new HashMap<>();
    }

    public <T extends Event> void addEventFilter(EventType<T> eventType, EventProcessor<T> eventFilter) {
        MultipleEventProcessor<T> processor =
                (MultipleEventProcessor<T>) processors.computeIfAbsent(eventType, ignored -> new MultipleEventProcessor<T>());
        processor.addEventFilter(eventFilter);
    }

    public <T extends Event> void removeEventFilter(EventType<T> eventType, EventProcessor<T> eventFilter) {
        MultipleEventProcessor<T> processor =
                (MultipleEventProcessor<T>) processors.computeIfAbsent(eventType, ignored -> new MultipleEventProcessor<T>());
        processor.removeEventFilter(eventFilter);
    }

    public <T extends Event> void addEventHandler(EventType<T> eventType, EventProcessor<T> eventHandler) {
        MultipleEventProcessor<T> processor =
                (MultipleEventProcessor<T>) processors.computeIfAbsent(eventType, ignored -> new MultipleEventProcessor<T>());
        processor.addEventHandler(eventHandler);
    }

    public <T extends Event> void removeEventHandler(EventType<T> eventType, EventProcessor<T> eventHandler) {
        MultipleEventProcessor<T> processor =
                (MultipleEventProcessor<T>) processors.computeIfAbsent(eventType, ignored -> new MultipleEventProcessor<T>());
        processor.removeEventHandler(eventHandler);
    }

    @Override
    public Event dispatchCapturingEvent(Event event) {
        EventType<? extends Event> eventType = event.getEventType();
        MultipleEventProcessor<? extends Event> eventProcessor = processors.get(eventType);
        if (eventProcessor != null)
            eventProcessor.dispatchCapturingEvent(event);
        return event;
    }

    @Override
    public Event dispatchBubblingEvent(Event event) {
        EventType<? extends Event> eventType = event.getEventType();
        MultipleEventProcessor<? extends Event> eventProcessor = processors.get(eventType);
        if (eventProcessor != null) {
            eventProcessor.dispatchBubblingEvent(event);
        }
        return event;
    }
}
