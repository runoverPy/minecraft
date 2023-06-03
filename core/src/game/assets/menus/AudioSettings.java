package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.Slider;
import game.assets.ui_elements.Widget;
import game.util.relay.FloatRelay;
import game.util.relay.IntRelay;

class AudioSettings extends MCUIMenu {
    public AudioSettings(MenuHandler handler) {
        super(Background.BRICKS);

        IntRelay volume = new IntRelay();

//        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
//            manager.insert(Widget.slider("Volume", slider1));
//            manager.insert(Widget.button("Back", handler::prev));
//        }

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
        volumeSlider.setSize(192, 16);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::prev);
        returnButton.setSize(192, 16);

        innerContainer.getChildren()
          .addAll(volumeSlider, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
