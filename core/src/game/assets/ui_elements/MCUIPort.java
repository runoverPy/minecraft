package game.assets.ui_elements;

import game.assets.event.MouseButton;
import game.assets.mcui.ContentRoot;
import game.assets.mcui.PixelComponent;
import game.assets.mcui.container.VerticalContainer;
import game.assets.event.EventTarget;
import game.assets.event.EventType;
import org.joml.Matrix4f;

import static game.assets.event.MouseEvent.MOUSE_RELEASED;
import static game.assets.event.MouseEvent.MOUSE_PRESSED;

public class MCUIPort extends ChildBox {
    private final ContentRoot mcuiContentRoot;

    public MCUIPort(int width, int height, int xOffset, int yOffset, Component parent, game.assets.mcui.Component root) {
        super(width, height, xOffset, yOffset, parent);
        mcuiContentRoot = new ContentRoot(root);
    }

    public MCUIPort(int width, int height, int xOffset, int yOffset, Component parent) {
        this(width, height, xOffset, yOffset, parent, new VerticalContainer());
    }

    public void setRoot(game.assets.mcui.Component newRoot) {
        this.mcuiContentRoot.setRoot(newRoot);
    }

    public game.assets.mcui.Component getRoot() {
        return this.mcuiContentRoot.getRoot();
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        mcuiContentRoot.getRoot().setSize(getWidth(), getHeight());
        mcuiContentRoot.getRoot().setLayoutPos(getCornerX(pxScale), getCornerY(pxScale));
        mcuiContentRoot.getRoot().render(matrix4f);
//        Scissor scissor = Scissor.cut(getCornerX(pxScale), getCornerY(pxScale), getWidth(pxScale), getHeight(pxScale));
//        scissor.close();
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        double x = event.mouseX, y = event.mouseY;
        EventTarget target = mcuiContentRoot.pick(x, y);
        EventType<game.assets.event.MouseEvent> type = switch (event.eventType) {
            case PRESSED -> MOUSE_PRESSED;
            case RELEASED -> MOUSE_RELEASED;
        };
        if (target != null)
            target.fireEvent(new game.assets.event.MouseEvent(type, MouseButton.Left, 0));
        return true;
    }
}
