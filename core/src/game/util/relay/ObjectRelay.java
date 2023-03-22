package game.util.relay;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectRelay<T> implements Supplier<T>, Consumer<T> {
    private T value;

    private final List<Consumer<T>> subscribers;

    public ObjectRelay(T startValue) {
        this.value = startValue;
        this.subscribers = new ArrayList<>();
    }

    public ObjectRelay() {
        this(null);
    }

    public void subscribe(Consumer<T> subscriber) {
        this.subscribers.add(subscriber);
    }

    public void setValue(T value) {
        this.value = value;
        subscribers.forEach(consumer -> consumer.accept(value));
    }

    public T getValue() {
        return value;
    }

    @Override
    public void accept(T t) {
        setValue(t);
    }

    @Override
    public T get() {
        return getValue();
    }
}
