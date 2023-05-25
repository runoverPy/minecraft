package game.assets.mcui.data;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ObservableListWrapper<T> extends AbstractList<T> implements ObservableList<T> {
    private final List<T> innerList;
    private final List<ListChangeListener<T>> changeListeners;

    public ObservableListWrapper(List<T> innerList) {
        this.innerList = innerList;
        changeListeners = new ArrayList<>();
    }

    @Override
    public T get(int index) {
        return innerList.get(index);
    }

    @Override
    public int size() {
        return innerList.size();
    }

    @Override
    public T set(int index, T element) {
        T replaced = innerList.set(index, element);
        fireListChange(new ListChangeListener.ListChange<>(this, replaced, index, ListChangeListener.ChangeType.REMOVE));
        fireListChange(new ListChangeListener.ListChange<>(this, element, index, ListChangeListener.ChangeType.INSERT));
        return replaced;
    }

    @Override
    public void add(int index, T element) {
        fireListChange(new ListChangeListener.ListChange<>(this, element, index, ListChangeListener.ChangeType.INSERT));
        innerList.add(index, element);
    }

    @Override
    public T remove(int index) {
        T removed = innerList.remove(index);
        fireListChange(new ListChangeListener.ListChange<>(this, removed, index, ListChangeListener.ChangeType.REMOVE));
        return removed;
    }

    @Override
    public void addListener(ListChangeListener<T> listener) {
        changeListeners.add(listener);
    }

    @Override
    public void removeListener(ListChangeListener<T> listener) {
        changeListeners.remove(listener);
    }

    private void fireListChange(ListChangeListener.ListChange<T> listChange) {
        for (ListChangeListener<T> changeListener : changeListeners)
            changeListener.accept(listChange);
    }
}
