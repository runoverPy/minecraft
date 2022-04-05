package game.assets.menus;

import game.assets.Background;
import game.assets.widgets.Widget;
import game.core.settings.GeneralSettings;
import game.main.Main;

class MainMenu extends Menu {
    public MainMenu(MenuHandler handler) {
        super(Background.BRICKS, 224, 224);

        try (WidgetManager manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.button("Singleplayer", () -> handler.next(new Singleplayer(handler))));
//            manager.insert(Widget.button("Multiplayer", () -> handler.next(new Multiplayer(handler))));
//            manager.insert(Widget.button("Settings", () -> handler.next(new GeneralSettingsMenu(handler))));
            manager.insert(Widget.button("Exit Game", () -> Main.getActiveWindow().close()));
        }
    }
}
