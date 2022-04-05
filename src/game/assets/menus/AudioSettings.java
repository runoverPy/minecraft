package game.assets.menus;

import game.assets.Background;
import game.assets.widgets.Widget;
import game.util.buffer.FloatBuffer;

class AudioSettings extends Menu {
    public AudioSettings(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);

        FloatBuffer slider1 = new FloatBuffer();

        try (WidgetManager manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.slider("Volume", slider1));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
