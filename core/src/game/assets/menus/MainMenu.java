package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Widget;
import game.core.settings.GeneralSettingsMenu;
import game.main.Main;

class MainMenu extends Menu {
    public MainMenu(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);

        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.button("Singleplayer", () -> handler.next(new WorldSelection(handler))));
//            manager.insert(Widget.button("Multiplayer", () -> handler.next(new Multiplayer(handler))));
//            manager.insert(Widget.button("Settings", () -> handler.next(new GeneralSettingsMenu(handler))));
            manager.insert(Widget.button("Settings", () -> handler.next(new GeneralSettingsMenu(handler))));
            manager.insert(Widget.button("Exit Game", () -> Main.getActiveWindow().close()));
//            manager.insert(Widget.button("Test MCUI", () -> handler.next(new MCUITest(handler))));
        }
    }
}
