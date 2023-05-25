package game.assets.mcui.data;

public interface SetChangeListener<T> extends ChangeListener<SetChangeListener.SetChange<T>> {
    class SetChange<T> extends Change<ObservableList<T>> {
        public SetChange(ObservableList<T> observable) {
            super(observable);
        }
    }
}
