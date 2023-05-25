package game.assets.event.dispatch;

import game.assets.event.Event;

/**
 * HEAD - node - ... - node - TAIL ; prev <- NODE -> next
 * Working direction:
 * End Point --> Entry Point
 */
public class LinkedEventDispatchChain implements EventDispatchChain {
    private LinkedNode head, tail;
    private int len = 0;

    private void pushHead(LinkedNode node) {
        if (node == null) return;
        if (len == 0) {
            head = tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
        len++;
    }

    private void pushTail(LinkedNode node) {
        if (node == null) return;
        if (len == 0) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        len++;
    }

    private LinkedNode popHead() {
        if (len == 0) return null;
        LinkedNode node = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            node.setNext(null);
        }
        len--;
        return node;
    }

    @Override
    public EventDispatchChain append(EventDispatcher dispatcher) {
        pushTail(new LinkedNode(dispatcher));
        return this;
    }

    @Override
    public EventDispatchChain prepend(EventDispatcher dispatcher) {
        pushHead(new LinkedNode(dispatcher));
        return this;
    }

    @Override
    public Event dispatchEvent(Event event) {
        if (len == 0) return event;

        LinkedNode formerHead = popHead();

        EventDispatcher nextDispatcher = formerHead.getDispatcher();
        Event result = nextDispatcher.dispatchEvent(event, this);

        pushHead(formerHead);

        return result;
    }

    @Override
    public void reset() {
        head = tail = null;
        len = 0;
    }

    private static class LinkedNode {
        EventDispatcher dispatcher;
        LinkedNode next;

        LinkedNode(EventDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public LinkedNode getNext() {
            return next;
        }

        public void setNext(LinkedNode next) {
            this.next = next;
        }

        public EventDispatcher getDispatcher() {
            return dispatcher;
        }

    }
}
