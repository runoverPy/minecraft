package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.Component;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.main.Main;

class MainMenu extends Menu {
    public MainMenu(MenuHandler handler) {
        super(Background.DIRT);
        setRoot(getContent(handler));
    }

    public static Component getContent(MenuHandler handler) {
        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        Button singlePlayerButton = new Button("Singleplayer");
        singlePlayerButton.setOnAction(event ->
          handler.next(new WorldSelection(handler)));
        singlePlayerButton.setPxSize(192, 16);
        Button multiPlayerButton = new Button("Multiplayer");
        multiPlayerButton.setOnAction(event ->
          handler.next(new Multiplayer(handler)));
        multiPlayerButton.setPxSize(192, 16);
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(() ->
          handler.next(new SettingsMenu(handler)));
        settingsButton.setPxSize(192, 16);
        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setOnAction(() -> Main.getActiveWindow().close());
        exitGameButton.setPxSize(192, 16);
        Button testMCUIButton = new Button("Test MCUI");
        testMCUIButton.setOnAction(event ->
          handler.next(new MCUITest(handler)));
        testMCUIButton.setPxSize(192, 16);
        Button testNoiseButton = new Button("Test Noise");
        testNoiseButton.setOnAction(event ->
          handler.next(new NoiseTest(handler)));
        testNoiseButton.setPxSize(192, 16);

        innerContainer.getChildren()
          .addAll(singlePlayerButton, multiPlayerButton, settingsButton, exitGameButton, testMCUIButton, testNoiseButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        return outerContainer;
    }
}
