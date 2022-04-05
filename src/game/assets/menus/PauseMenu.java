package game.assets.menus;

import game.assets.Background;
import game.assets.widgets.Widget;
import game.main.Main;

import java.io.IOException;

class PauseMenu extends Menu {
    public PauseMenu(PauseHandler handler) {
        super(Background.GRAY, 256, 256);

        Runnable toMainMenu = () -> {
            try {
                Main.closeGame();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            Main.setScene(MenuHandler.mainMenu());
        };

        try (WidgetManager manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.button("Return to game", handler::deactivate));
//            manager.insert(Widget.button("Settings", () -> handler.next(new GeneralSettingsMenu(handler))));
            manager.insert(Widget.button("Main Menu", toMainMenu));
        }
    }
}
