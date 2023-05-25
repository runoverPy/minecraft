package game.assets.event.dispatch;

import game.assets.event.*;
import game.assets.mcui.Component;

import java.util.*;

public final class InputMap<T extends Component> implements EventProcessor<Event> {

    private final T host;
    private final List<Mapping<?>> currentMappings;

    private InputMap<T> parent;
    private final List<InputMap<T>> children = new ArrayList<>();

    public InputMap(T host) {
        this.host = host;

        currentMappings = new LinkedList<>();
    }

    public void addEventMapping(Mapping<?>... mappings) {
        // register mapping to host eventHandler
        // TODO: 16.05.23 add more stuff here, registering this as event handler for host for the given event types
        // add mapping to currentMappings

        for (Mapping<?> mapping : mappings) {
            if (!currentMappings.contains(mapping)) {
                currentMappings.add(mapping);
                addEventHandler(mapping.getEventType());
            }
        }
    }

    private void addEventHandler(EventType<?> eventType) {
        host.addEventHandler(eventType, this::process);
    }

    @Override
    public void process(Event event) {
        // get corresponding event handler from mappings
        // ordered by relevance (i.e. order, type, depth)
        if (event == null || event.isCaptured()) return;

        Queue<Mapping<?>> mappingQueue = lookup(event);
        for (Mapping<?> mapping : mappingQueue) {
            EventProcessor handler = mapping.getEventHandler();
            if (handler != null) handler.process(event);
            if (mapping.isAutoCapture()) event.capture();
            if (event.isCaptured()) break;
        }
    }

    /**
     * get queue of events (todo: in order of relevance)
     * @param event
     * @return
     */
    private Queue<Mapping<?>> lookup(Event event) {
        // find all event handlers related to this event,
        Comparator<Mapping<?>> comparator = (o1, o2) -> {
            if (!event.getEventType().isSubType(o1.eventType) || !event.getEventType().isSubType(o2.eventType))
                throw new IllegalArgumentException();
            if (o1 == o2) return 0;
            if (o1.eventType == o2.eventType) return 0;
            else {

            }
            return 0;
        };
        Queue<Mapping<?>> results = new LinkedList<>();
        recursiveLookup(event, results);
        return results;
    }

    private void recursiveLookup(Event event, Queue<Mapping<?>> mappingQueue) {
        for (Mapping<?> mapping : currentMappings) {
            if (mapping.getEventType().isSubType(event.getEventType()))
                mappingQueue.add(mapping);
        }
        for (InputMap<?> childMap : children) {
            childMap.recursiveLookup(event, mappingQueue);
        }
    }

    public abstract static class Mapping<T extends Event> implements Comparable<Mapping<Event>> {
        private final EventType<T> eventType;
        private final EventProcessor<T> eventProcessor;

        private boolean autoCapture;

        private Mapping(EventType<T> eventType, EventProcessor<T> eventProcessor) {
            this.eventType = eventType;
            this.eventProcessor = eventProcessor;
        }

        public EventType<T> getEventType() {
            return eventType;
        }

        public EventProcessor<T> getEventHandler() {
            return eventProcessor;
        }

        public boolean isAutoCapture() {
            return autoCapture;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Mapping<?> mapping = (Mapping<?>) o;
            return Objects.equals(eventType, mapping.eventType) && Objects.equals(eventProcessor, mapping.eventProcessor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(eventType);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[type: " + getEventType().getName() + "]";
        }
    }

    public static class MouseMapping extends Mapping<MouseEvent> {
        public MouseMapping(EventType<MouseEvent> eventType, EventProcessor<MouseEvent> eventProcessor) {
            super(eventType, eventProcessor);
        }

        @Override
        public int compareTo(Mapping<Event> o) {
            return 0;
        }
    }

    public static class KeyMapping extends Mapping<KeyEvent> {
        public KeyMapping(EventType<KeyEvent> eventType, EventProcessor<KeyEvent> eventProcessor) {
            super(eventType, eventProcessor);
        }

        @Override
        public int compareTo(Mapping<Event> o) {
            return 0;
        }
    }
}
