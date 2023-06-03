package game.core.settings;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.Slider;
import game.assets.menus.MCUIMenu;
import game.assets.menus.MenuHandler;
import game.main.Main;
import game.util.relay.FloatRelay;
import game.util.relay.IntRelay;

public class GeneralSettingsMenu extends MCUIMenu {
    public GeneralSettingsMenu(MenuHandler handler) {
        super(Background.GRAY);
        GeneralSettings generalSettings = Main.getSettings();
        System.out.println(generalSettings);

        IntRelay fov = new IntRelay(generalSettings.getFOV());
        fov.subscribe(generalSettings::setFOV);
        FloatRelay dpi = new FloatRelay(generalSettings.getDPI());
        dpi.subscribe(generalSettings::setDPI);

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        Slider<Integer> fovSlider = new Slider<>();
        fovSlider.setName("FOV");
        fovSlider.setTransformer(new Slider.IntTransformer(60, 120));
        fovSlider.setValueRelay(fov);
        fovSlider.setSize(192, 16);
        Slider<Float> dpiSlider = new Slider<>();
        dpiSlider.setName("Sensitivity");
        dpiSlider.setTransformer(new Slider.FloatTransformer(10));
        dpiSlider.setValueRelay(dpi);
        dpiSlider.setSize(192, 16);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(() -> {
            generalSettings.save();
            handler.prev();
        });
        returnButton.setSize(192, 16);

        innerContainer.getChildren()
          .addAll(fovSlider, dpiSlider, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
