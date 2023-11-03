package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.container.HorizontalContainer;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.Slider;
import game.assets.mcui.control.Switch;
import game.util.relay.BoolRelay;
import game.util.relay.IntRelay;

class VideoSettings extends Menu {
    public VideoSettings(MenuHandler handler) {
        super(Background.BRICKS);

        BoolRelay vsync = new BoolRelay();
        IntRelay fov = new IntRelay();

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        HorizontalContainer line0 = new HorizontalContainer();
        line0.setAlign(Align.CENTER);
        line0.setSize(-1, -1);
        line0.setSpacing(2);
        Switch vsyncButton = new Switch("VSync", vsync);
        vsyncButton.setPxSize(95, 16);
        Slider<Integer> fovSlider = new Slider<>();
        fovSlider.setName("FOV");
        fovSlider.setTransformer(new Slider.IntTransformer(60, 120));
        fovSlider.setPxSize(95, 16);
        fovSlider.setValueRelay(fov);
        line0.getChildren()
          .addAll(vsyncButton, fovSlider);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::prev);
        returnButton.setPxSize(192, 16);

        innerContainer.getChildren()
          .addAll(line0, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
