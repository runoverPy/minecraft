package game.assets.menus;

import game.assets.Background;
import game.assets.widgets.Widget;
import game.main.Main;

public class ErrorScreen extends Menu {
    public ErrorScreen(Throwable error) {
        super(Background.STEINLE, 224, 224);

        try (WidgetManager manager = organiser(192, 18, 4, 4)){
            manager.insert(Widget.textBox(error.toString(), true, true));
            manager.insert(Widget.button("Return to Menu", Main::mainMenu));
        }

//        TableOrganizer organizer = tableOrganizer(8, 8, 24, 24);
//
//        organizer.insert(Widget.button("Return to Menu", Main::mainMenu), 0, 8, 6, 1);
    }
}
