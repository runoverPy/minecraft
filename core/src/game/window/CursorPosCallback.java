package game.window;

@FunctionalInterface
public interface CursorPosCallback extends WindowCallback {
    void invoke(double xPos, double yPos);
}
