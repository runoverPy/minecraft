package game.window;

public interface WindowSizeCallback extends WindowCallback {
    void invoke(int width, int height);
}
