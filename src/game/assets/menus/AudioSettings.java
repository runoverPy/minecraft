package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Widget;
import game.util.relay.FloatRelay;

class AudioSettings extends Menu {
    public AudioSettings(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);

        FloatRelay slider1 = new FloatRelay();

        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.slider("Volume", slider1));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
