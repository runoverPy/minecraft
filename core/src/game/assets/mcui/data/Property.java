package game.assets.mcui.data;

public class Property<T> implements ObservableValue<T> {
    private T value;

    @Override
    public void addListener(ValueChangeListener<T> listener) {

    }

    @Override
    public void removeListener(ValueChangeListener<T> listener) {

    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    private void fireValueChange(ValueChangeListener.ValueChange<T> valueChange) {

    }
}


