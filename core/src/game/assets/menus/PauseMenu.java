package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Widget;
import game.core.settings.GeneralSettingsMenu;
import game.main.Main;

class PauseMenu extends UIELMenu {
    public PauseMenu(PauseHandler handler) {
        super(Background.GRAY, 256, 256);

        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.button("Return to game", handler::deactivate));
            manager.insert(Widget.button("Settings", () -> handler.next(new GeneralSettingsMenu(handler))));
            manager.insert(Widget.button("Main Menu", Main::closeGame));
        }
    }
}
