package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Widget;
import game.util.relay.BoolRelay;
import game.util.relay.FloatRelay;

class VideoSettings extends Menu {
    public VideoSettings(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);

        BoolRelay vsync = new BoolRelay();
        FloatRelay fov = new FloatRelay();

        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
            manager.insert(
                    Widget.button("VSync", vsync), Widget.slider("Field of View", fov));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
