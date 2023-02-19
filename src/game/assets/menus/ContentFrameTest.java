package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Button;
import game.assets.ui_elements.ColorBox;
import game.assets.ui_elements.ContentFrame;
import game.util.relay.IntRelay;
import org.joml.Vector4f;

public class ContentFrameTest extends Menu {
    public ContentFrameTest(MenuHandler handler) {
        super(Background.BRICKS, 224, 224);

        IntRelay intBuffer = new IntRelay();

        ContentFrame frame = new ContentFrame(128, 128, (getRoot().getWidth() - 128) / 2, (getRoot().getHeight() - 128) / 2, getRoot());
        frame.addChild(new Button(128, 18, 0, 0, frame, "Add new field", () -> {
            frame.addChild(new ColorBox(128, 18, 0, frame.length(), null, new Vector4f(
              intBuffer.get() % 2,
              intBuffer.get() % 4 / 2,
              intBuffer.get() % 8 / 4,
              1
            )));
            intBuffer.setValue(intBuffer.getValue() + 1);
        }));
        frame.addChild(new Button(128, 18, 0, frame.length(), frame, "Exit Test", handler::prev));
        insert(frame);
    }



//    private static Vector4f rainbow(int value) {}
}
