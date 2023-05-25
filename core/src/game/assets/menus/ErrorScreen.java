package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Widget;

public class ErrorScreen extends UIELMenu {
    public ErrorScreen(MenuHandler handler, Throwable error) {
        super(Background.STEINLE, 224, 224);

        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)){
            manager.insert(Widget.textBox(error.toString(), true, true));
            manager.insert(Widget.button("Return to Menu", handler::prev));
        }

//        TableOrganizer organizer = tableOrganizer(8, 8, 24, 24);
//
//        organizer.insert(Widget.button("Return to Menu", Main::mainMenu), 0, 8, 6, 1);
    }
}
