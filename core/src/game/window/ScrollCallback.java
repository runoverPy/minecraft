package game.window;

@FunctionalInterface
public interface ScrollCallback extends WindowCallback {
    void invoke(double xOffset, double yOffset);
}
