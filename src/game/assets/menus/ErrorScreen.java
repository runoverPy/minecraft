package game.assets.menus;

import game.assets.Background;
import game.assets.Scene;

public class ErrorScreen extends Menu {
    private final Throwable error;

    public ErrorScreen(Throwable error) {
        super(Background.STEINLE, 224, 224);
        this.error = error;
    }
}
