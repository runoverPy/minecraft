package game.assets.mcui.data;

public interface ObservableValue<T> extends Observable<ValueChangeListener<T>> {
    T get();
    void set(T value);
}
