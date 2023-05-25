package game.assets.mcui.data;

public interface ListChangeListener<T> extends ChangeListener<ListChangeListener.ListChange<T>> {
    class ListChange<T> extends ChangeListener.Change<ObservableList<T>> {
        private final T changeValue;
        private final int changeIndex;
        private final ChangeType changeType;

        public ListChange(ObservableList<T> observableList, T changeValue, int changeIndex, ChangeType changeType) {
            super(observableList);
            this.changeValue = changeValue;
            this.changeIndex = changeIndex;
            this.changeType = changeType;
        }

        public T getValue() {
            return changeValue;
        }

        public int getIndex() {
            return changeIndex;
        }

        public ChangeType getType() {
            return changeType;
        }
    }

    enum ChangeType {
        INSERT, REMOVE
    }
}
