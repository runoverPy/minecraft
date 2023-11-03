package game.util;

public interface DeepCopy<T> extends Copy<T> {
    default T deepcopy() {
        return copy();
    }
}
