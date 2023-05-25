package game.window;

@FunctionalInterface
public interface KeyCallback extends WindowCallback {
    void invoke(int key, int scancode, int action, int mods);
}
