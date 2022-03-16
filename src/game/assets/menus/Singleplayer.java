package game.assets.menus;

import game.assets.Background;
import game.assets.widgets.Widget;
import game.core.Game;
import game.main.Main;

class Singleplayer extends Menu {
    public Singleplayer(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);

        try (WidgetManager manager = organiser(192, 22, 4, 4)) {
            manager.insert(Widget.button("Enter test game", () -> Main.setScene(Game.getInstance())));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
