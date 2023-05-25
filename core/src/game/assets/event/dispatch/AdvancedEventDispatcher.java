package game.assets.event.dispatch;

import game.assets.event.Event;

public abstract class AdvancedEventDispatcher extends BasicEventDispatcher {

    protected abstract LinkedNode<?> getHead();

    protected abstract LinkedNode<?> getTail();

    @Override
    public final Event dispatchCapturingEvent(Event event) {
        LinkedNode<?> node = getHead();
        while (node != null) {
            node.getDispatcher().dispatchCapturingEvent(event);
            if (event.isCaptured()) break;
            node = node.getNext();
        }
        return event;
    }

    @Override
    public final Event dispatchBubblingEvent(Event event) {
        LinkedNode<?> node = getTail();
        while (node != null) {
            node.getDispatcher().dispatchBubblingEvent(event);
            if (event.isCaptured()) break;
            node = node.getPrev();
        }
        return event;
    }

    protected static class LinkedNode<T extends EventDispatcher> {
        T dispatcher;
        LinkedNode<?> prev, next;

        LinkedNode(T dispatcher) {
            this.dispatcher = dispatcher;
        }

        public LinkedNode<?> getPrev() {
            return prev;
        }

        public LinkedNode<?> getNext() {
            return next;
        }

        public void setPrev(LinkedNode<?> prev) {
            this.prev = prev;
        }

        public void setNext(LinkedNode<?> next) {
            this.next = next;
        }

        public T getDispatcher() {
            return dispatcher;
        }

        public void insertPrev(EventDispatcher dispatcher) {
            LinkedNode<?> node = new LinkedNode<>(dispatcher);
            if (prev != null) prev.next = node;
            node.prev = prev;
            this.prev = node;
            node.next = this;
        }

        public void insertNext(EventDispatcher dispatcher) {
            LinkedNode<?> node = new LinkedNode<>(dispatcher);
            if (next != null) next.prev = node;
            node.next = next;
            this.next = node;
            node.prev = this;
        }

        public EventDispatcher removeThis() {
            prev.next = next;
            next.prev = prev;
            prev = next = null;
            return dispatcher;
        }
    }
}
