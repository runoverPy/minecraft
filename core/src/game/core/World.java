package game.core;

public interface World<C, M> {
    M get(C coordinate);

    void set(C coordinate, M material);
}
