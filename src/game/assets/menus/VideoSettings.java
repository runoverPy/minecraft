package game.assets.menus;

import game.assets.Background;
import game.assets.widgets.Widget;
import game.util.buffer.BoolBuffer;
import game.util.buffer.FloatBuffer;

class VideoSettings extends Menu {
    public VideoSettings(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);

        BoolBuffer vsync = new BoolBuffer();
        FloatBuffer fov = new FloatBuffer();

        try (WidgetManager manager = organiser(192, 18, 4, 4)) {
            manager.insert(
                    Widget.switchButton("VSync", vsync), Widget.slider("Field of View", fov));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
