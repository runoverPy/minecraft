package game.assets.mcui.data;

public interface ObservableList<T> extends AdvancedList<T>, Observable<ListChangeListener<T>> {}
