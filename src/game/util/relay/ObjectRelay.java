package game.util.relay;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectRelay<T> implements Supplier<T>, Consumer<T> {
    private T value;

    public ObjectRelay(T startValue) {
        this.value = startValue;
    }

    public ObjectRelay() {
        this(null);
    }

    public void setValue(T value) {
        this.value = value;
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
