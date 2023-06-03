package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.ui_elements.Widget;
import game.core.settings.GeneralSettingsMenu;
import game.main.Main;

class PauseMenu extends MCUIMenu {
    public PauseMenu(PauseHandler handler) {
        super(Background.GRAY);

//        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
//            manager.insert(Widget.button("Return to game", handler::deactivate));
//            manager.insert(Widget.button("Settings", () -> handler.next(new GeneralSettingsMenu(handler))));
//            manager.insert(Widget.button("Main Menu", Main::closeGame));
//        }

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::deactivate);
        returnButton.setSize(192, 16);
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(() -> handler.next(new SettingsMenu(handler)));
        settingsButton.setSize(192, 16);
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(Main::closeGame);
        mainMenuButton.setSize(192, 16);

        innerContainer.getChildren()
          .addAll(returnButton, settingsButton, mainMenuButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
