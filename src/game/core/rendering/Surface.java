package game.core.rendering;

import game.util.Fn;

public interface Surface {
    void bindTexture();
    void loseTexture();

    default void withTexture(Fn action) {
        bindTexture();
        action.call();
        loseTexture();
    }
}
