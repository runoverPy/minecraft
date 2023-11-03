package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.core.settings.GeneralSettingsMenu;

class SettingsMenu extends Menu {
    public SettingsMenu(MenuHandler handler) {
        super(Background.BRICKS);

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        Button generalSettingsButton = new Button("General Settings");
        generalSettingsButton.setOnAction(() ->
          handler.next(new GeneralSettingsMenu(handler)));
        generalSettingsButton.setPxSize(192, 16);
        Button audioSettingsButton = new Button("Audio Settings");
        audioSettingsButton.setOnAction(() ->
          handler.next(new AudioSettings(handler)));
        audioSettingsButton.setPxSize(192, 16);
        Button videoSettingsButton = new Button("Video Settings");
        videoSettingsButton.setOnAction(() ->
          handler.next(new VideoSettings(handler)));
        videoSettingsButton.setPxSize(192, 16);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::prev);
        returnButton.setPxSize(192, 16);

        innerContainer.getChildren()
          .addAll(generalSettingsButton, audioSettingsButton, videoSettingsButton, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
