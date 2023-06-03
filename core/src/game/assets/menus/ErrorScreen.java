package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.asset.TextTile;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.ui_elements.Widget;

public class ErrorScreen extends MCUIMenu {
    public ErrorScreen(MenuHandler handler, Throwable error) {
        super(Background.STEINLE);

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        TextTile errorTextTile = new TextTile();
        errorTextTile.setText(error.toString());
        errorTextTile.setSize(192, 36);
        errorTextTile.setAlign(Align.BOTTOM_CENTER);
        Button returnButton = new Button("Return to Menu");
        returnButton.setOnAction(handler::prev);
        returnButton.setSize(192, 16);

        innerContainer.getChildren()
          .addAll(errorTextTile, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
