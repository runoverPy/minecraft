package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.ColorBox;
import game.assets.ui_elements.Container;
import game.assets.ui_elements.Widget;
import game.core.GameManager;
import game.main.Main;
import game.util.relay.BoolRelay;
import org.joml.Matrix4f;

public class WorldSelection extends Menu {
    private Container topBar, midBar, botBar;
    private ColorBox selectorFrame;

    public WorldSelection(MenuHandler handler) {
        super(Background.DIRT, 16 * 20, 12 * 20);
        TableOrganizer organizer = tableOrganizer(16, 12, 20, 20, 1);
        organizer.insert(Widget.textBox("Select World:", false, true), 4, 8, 0, 1);
        organizer.insert(Widget.query(new StringBuffer()), 4, 8, 1, 1);
        organizer.insert(Widget.button("Enter Game", () -> {}, new BoolRelay(false)), 2, 4, 10, 1);
        organizer.insert(Widget.button("Demo Game", () -> Main.openGame(GameManager.demoGame())), 6, 4, 10, 1);
        organizer.insert(Widget.button("New Game", () -> {}, new BoolRelay(false)), 10, 4, 10, 1);
        organizer.insert(Widget.button("Return", handler::prev), 4, 8, 11, 1);

//        insert(new ColorBox(0, 0, 0, 0, null, new Vector4f(0, 0, 1, 1)));
//        topBar = new Container(0, 64, 0, 0, getRoot());
//        midBar = new Container(0, 0, 0, 0, getRoot()) {
//            @Override
//            public int getYOffset(int pxScale) {
//                return topBar.getHeight(pxScale);
//            }
//
//            @Override
//            public int getHeight(int pxScale) {
//                return getParent().getHeight(pxScale) - (topBar.getHeight(pxScale) + botBar.getHeight(pxScale));
//            }
//        };
//        botBar = new Container(0, 64, 0, 0, getRoot()) {
//            @Override
//            public int getXOffset() {
//                return topBar.getHeight() + midBar.getHeight();
//            }
//        };
//
//        selectorFrame = new ColorBox(0, 0, 0, 0, midBar, new Vector4f(0, 0, 0, 0.5f));
//        DropDownSelector<GameSave> saveSelector = new DropDownSelector<>(0, 0, 0, 0, midBar, new ObjectBuffer<>());
//        midBar.addChild(selectorFrame);
//        midBar.addChild(saveSelector);
//
//        insert(topBar);
//        insert(midBar);
//        insert(botBar);
    }

    @Override
    public void render(Matrix4f matrix4f) {
        int minSelectorHeight = 16;
        int pxScale = 0;

        super.render(matrix4f);
    }
}
