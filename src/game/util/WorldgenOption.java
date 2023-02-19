package game.util;

public interface WorldgenOption<E extends Enum<E>> extends Cyclic<E> {
    String variantName();
}
