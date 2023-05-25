package game.assets.mcui.data;

public interface ValueChangeListener<T> extends ChangeListener<ValueChangeListener.ValueChange<T>> {
    class ValueChange<T> extends Change<ObservableValue<T>> {
        private final T oldValue, newValue;

        public ValueChange(ObservableValue<T> observable, T oldValue, T newValue) {
            super(observable);
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public T getOldValue() {
            return oldValue;
        }

        public T getNewValue() {
            return newValue;
        }
    }
}
