package game.window;

@FunctionalInterface
public interface MouseButtonCallback extends WindowCallback {
    void invoke(int button, int action, int mods);
}
