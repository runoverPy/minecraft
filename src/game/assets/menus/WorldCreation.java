package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Widget;

public class WorldCreation extends Menu {
    public WorldCreation(MenuHandler handler) {
        super(Background.DIRT, 224, 224);
        TableOrganizer organizer = tableOrganizer(224 / 18, 224 / 18, 18, 18);
        organizer.insert(Widget.button("Enter Game", () -> {}), 4, 2, 11, 1);
        organizer.insert(Widget.button("New Game", () -> {}), 6, 2, 11, 1);
    }
}
