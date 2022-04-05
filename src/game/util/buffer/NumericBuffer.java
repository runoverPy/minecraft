package game.util.buffer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class NumericBuffer<T> implements Supplier<T>, Consumer<T> {
    private T value;
    private final Runnable onUpdate;

    public NumericBuffer(T startValue, Runnable onUpdate) {
        this.value = startValue;
        this.onUpdate = onUpdate;
    }

    public NumericBuffer(T startValue) {
        this(startValue, () -> {});
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
