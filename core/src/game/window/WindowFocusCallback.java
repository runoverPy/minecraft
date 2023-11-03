package game.window;

public interface WindowFocusCallback extends WindowCallback {
    void invoke(boolean focused);
}
