package game.assets.menus;

import game.assets.Background;
import game.assets.Callback;
import game.assets.ui_elements.Widget;
import game.core.settings.GeneralSettings;
import game.main.Main;
import game.util.relay.FloatRelay;
import game.util.relay.IntRelay;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class GeneralSettingsMenu extends Menu {
    public GeneralSettingsMenu(MenuHandler handler) {
        super(Background.GRAY, 224, 224);
        GeneralSettings generalSettings = Main.getSettings();

        IntRelay fov = new IntRelay(generalSettings.getFOV());
        FloatRelay sensitivity = new FloatRelay(generalSettings.getDPI());

        Runnable onClose = () -> {
            generalSettings.setFOV(fov.getValue());
            generalSettings.setDPI(sensitivity.getValue());
            generalSettings.save();
            handler.prev();
        };

        callbacks.add(new Callback(GLFW_KEY_ESCAPE, onClose));

        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.slider("Field of View", fov, 60, 120));
            manager.insert(Widget.slider("Mouse Sensitivity", sensitivity, 0, 10));
            manager.insert(Widget.button("Back", onClose));
        }

    }
}
