package game.util;

public interface Fn {
    Fn Null = () -> {};

    void call();
    static void none() {}
}
