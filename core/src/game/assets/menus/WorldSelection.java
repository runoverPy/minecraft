package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.atoms.TextTile;
import game.assets.mcui.container.HorizontalContainer;
import game.assets.mcui.container.ScrollPane;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.InputField;
import game.core.GameManager;
import game.core.GameRuntime;
import game.main.Main;

public class WorldSelection extends Menu {

    public WorldSelection(MenuHandler handler) {
        super(Background.DIRT);

        boolean demoGameExists = GameRuntime.getInstance().getGeneratorRegister().isGeneratorRegistered("vanilla:flat_test");

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        TextTile title = new TextTile("Select World");
        title.setAlign(Align.BOTTOM_CENTER);
        title.setShaded(true);
        title.setPxSize(192, 16);
        InputField lookupField = new InputField();
        lookupField.setPxSize(192, 16);
        ScrollPane saves = new ScrollPane();
        saves.setPxSize(224, 192);
        // todo add VerticalContainer to saves;
        HorizontalContainer buttons = new HorizontalContainer();
        buttons.setAlign(Align.CENTER);
        buttons.setSize(-1, -1);
        buttons.setSpacing(2);
        Button demoWorldButton = new Button("Demo Game");
        demoWorldButton.setPxSize(95, 16);
        if (demoGameExists) demoWorldButton.setOnAction(() ->
          Main.openGame(GameManager.demoGame()));
        Button createWorldButton = new Button("New Game");
        createWorldButton.setPxSize(95, 16);
        createWorldButton.setOnAction(() ->
          handler.next(new WorldCreation(handler)));
        buttons.getChildren()
          .addAll(demoWorldButton, createWorldButton);
        Button returnButton = new Button("Back");
        returnButton.setPxSize(128, 16);
        returnButton.setOnAction(handler::prev);

        innerContainer.getChildren()
          .addAll(title, lookupField, saves, buttons, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
