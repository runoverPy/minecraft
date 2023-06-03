package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.asset.ColorBox;
import game.assets.ui_elements.container.Container;
import game.assets.ui_elements.Widget;
import game.core.GameManager;
import game.core.GameRuntime;
import game.main.Main;
import game.util.relay.BoolRelay;
import org.joml.Matrix4f;

public class WorldSelection extends UIELMenu {
    private Container topBar, midBar, botBar;
    private ColorBox selectorFrame;

    public WorldSelection(MenuHandler handler) {
        super(Background.DIRT, 16 * 20, 12 * 20);
        boolean demoGameExists = GameRuntime.getInstance().getGeneratorRegister().isGeneratorRegistered("vanilla:flat_test");

        TableOrganizer organizer = tableOrganizer(16, 12, 20, 20, 1);
        organizer.insert(
          Widget.textBox("Select World:", false, true),
          4, 8, 0, 1);
        organizer.insert(
          Widget.query(new StringBuffer()),
          4, 8, 1, 1);
        organizer.insert(
          Widget.cFrame(),
          2, 12, 2, 8);
        organizer.insert(
          Widget.button("Enter Game", () -> {}, new BoolRelay(false)),
          2, 4, 10, 1);
        if (demoGameExists) organizer.insert(
          Widget.button("Demo Game", () -> Main.openGame(GameManager.demoGame())),
          6, 4, 10, 1);
        organizer.insert(
          Widget.button("New Game", () -> handler.next(new WorldCreation(handler))),
          10, 4, 10, 1);
        organizer.insert(
          Widget.button("Return", handler::prev),
          4, 8, 11, 1);
    }

    @Override
    public void render(Matrix4f matrix4f) {
        int minSelectorHeight = 16;
        int pxScale = 0;

        super.render(matrix4f);
    }
}
