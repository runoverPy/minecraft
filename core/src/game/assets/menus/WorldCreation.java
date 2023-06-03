package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.container.HorizontalContainer;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.ui_elements.Widget;
import game.util.relay.BoolRelay;
import game.util.relay.ObjectRelay;

import java.util.ArrayList;

public class WorldCreation extends MCUIMenu {
    public WorldCreation(MenuHandler handler) {
        super(Background.DIRT);

//        TableOrganizer organizer = tableOrganizer(16, 16, 20, 20, 1);
//        organizer.insert(Widget.button("Enter Game", () -> {}, new BoolRelay(false)), 4, 4, 10, 1);
//        organizer.insert(Widget.button("New Game", () -> {}, new BoolRelay(false)), 8, 4, 10, 1);
//        organizer.insert(Widget.button("Exit", handler::prev), 5, 6, 11, 1);
//        organizer.insert(Widget.dropDown("World Generator", new ObjectRelay<>(), new ArrayList<>()), 8, 4, 3, 1);

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
        enterGameButton.setSize(95, 16);
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(() -> {});
        newGameButton.setSize(95, 16);
        line0.getChildren()
            .addAll(enterGameButton, newGameButton);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::prev);
        returnButton.setSize(192, 16);

        innerContainer.getChildren()
          .addAll(line0, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
