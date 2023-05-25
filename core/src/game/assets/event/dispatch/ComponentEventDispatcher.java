package game.assets.event.dispatch;

public class ComponentEventDispatcher extends AdvancedEventDispatcher {
    private LinkedNode<?> head;
    private LinkedNode<ProcessorEventDispatcher> tail;

    public ComponentEventDispatcher() {
        this.head = new LinkedNode<EventDispatcher>(new IgnoredEventDispatcher());
        this.tail = new LinkedNode<>(new ProcessorEventDispatcher());
    }

    @Override
    protected LinkedNode<?> getHead() {
        return head;
    }

    @Override
    protected LinkedNode<?> getTail() {
        return tail;
    }

    public ProcessorEventDispatcher getProcessorEventDispatcher() {
        return tail.getDispatcher();
    }
}
