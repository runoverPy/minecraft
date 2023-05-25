package game.assets.menus;

import game.assets.Background;
import game.assets.Callback;
import game.assets.ui_elements.Widget;

import static org.lwjgl.glfw.GLFW.*;

class Settings extends UIELMenu {
    public Settings(MenuHandler handler) {
        super(Background.BRICKS, 224, 224);

        callbacks.add(new Callback(GLFW_KEY_ESCAPE, handler::prev));

        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.button("Audio Settings", () -> handler.next(new AudioSettings(handler))));
            manager.insert(Widget.button("Video Settings", () -> handler.next(new VideoSettings(handler))));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
