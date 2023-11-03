package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.Component;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.Slider;
import game.util.relay.IntRelay;

class AudioSettings extends Menu {
    public AudioSettings(MenuHandler handler) {
        super(Background.BRICKS);
        setRoot(getContent(handler));
    }

    public static Component getContent(MenuHandler handler) {
        IntRelay volume = new IntRelay();

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        Slider<Integer> volumeSlider = new Slider<>();
        volumeSlider.setName("Volume");
        volumeSlider.setTransformer(new Slider.IntTransformer(100));
        volumeSlider.setValueRelay(volume);
        volumeSlider.setPxSize(192, 16);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::prev);
        returnButton.setPxSize(192, 16);

        innerContainer.getChildren()
          .addAll(volumeSlider, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        return outerContainer;
    }
}
