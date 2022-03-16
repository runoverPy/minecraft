package game.assets.menus;

import game.assets.Background;
import game.assets.Callback;
import game.assets.widgets.Button;
import game.assets.widgets.Widget;

import static org.lwjgl.glfw.GLFW.*;

class Settings extends Menu {
    public Settings(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);

        callbacks.add(new Callback(GLFW_KEY_ESCAPE, handler::prev));

        try (WidgetManager manager = organiser(192, 22, 4, 4)) {
            manager.insert(Widget.button("Audio Settings", () -> handler.next(new AudioSettings(handler))));
            manager.insert(Widget.button("Video Settings", () -> handler.next(new VideoSettings(handler))));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
