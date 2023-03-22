package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Widget;
import game.util.relay.BoolRelay;
import game.util.relay.ObjectRelay;

import java.util.ArrayList;

public class WorldCreation extends Menu {
    public WorldCreation(MenuHandler handler) {
        super(Background.DIRT, 224, 224);
        TableOrganizer organizer = tableOrganizer(16, 16, 20, 20, 1);
        organizer.insert(Widget.button("Enter Game", () -> {}, new BoolRelay(false)), 4, 4, 10, 1);
        organizer.insert(Widget.button("New Game", () -> {}, new BoolRelay(false)), 8, 4, 10, 1);
        organizer.insert(Widget.button("Exit", handler::prev), 5, 6, 11, 1);
        organizer.insert(Widget.dropDown("World Generator", new ObjectRelay<>(), new ArrayList<>()), 8, 4, 3, 1);
    }
}
