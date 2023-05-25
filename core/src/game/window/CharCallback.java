package game.window;

@FunctionalInterface
public interface CharCallback extends WindowCallback {
    void invoke(int codepoint);
}
