package game.assets.mcui.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * List specification expansion which contains multiple convenience methods,
 * with default implementations using abstract methods specified in List
 * @param <T> the type of elements in this list
 */
public interface AdvancedList<T> extends List<T> {
    default boolean addAll(T... elements) {
        return addAll(Arrays.asList(elements));
    }

    default boolean setAll(T... elements) {
        return setAll(Arrays.asList(elements));
    }

    default boolean setAll(Collection<? extends T> elements) {
        if (isEmpty() && elements.isEmpty()) return false;
        clear();
        return addAll(elements);
    }

    default boolean removeAll(T... elements) {
        return removeAll(Arrays.asList(elements));
    }

    default boolean retainAll(T... elements) {
        return retainAll(Arrays.asList(elements));
    }

    default void remove(int from, int to) {
        ListIterator<T> iter = listIterator(from);
        for (int i = from; i < to; i++) {
            iter.next();
            iter.remove();
        }
    }
}
