package game.assets.event;

@FunctionalInterface
public interface EventRunnable<T extends Event> extends EventProcessor<T> {
    default void process(T event) {
        process();
    }

    void process();

    static EventRunnable<?> wrap(Runnable runnable) {
        return runnable::run;
    }
}
