package game.assets.event;

@FunctionalInterface
public interface EventProcessor<T extends Event> {
    void process(T event);
}
