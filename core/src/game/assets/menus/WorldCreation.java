package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.container.HorizontalContainer;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;

public class WorldCreation extends Menu {
    public WorldCreation(MenuHandler handler) {
        super(Background.DIRT);

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
        Button enterGameButton = new Button("Enter Game");
        enterGameButton.setOnAction(() -> {});
        enterGameButton.setPxSize(95, 16);
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(() -> {});
        newGameButton.setPxSize(95, 16);
        line0.getChildren()
            .addAll(enterGameButton, newGameButton);
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
