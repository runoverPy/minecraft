package game.assets.ui_elements;

import game.util.relay.BoolRelay;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class Selectable extends ChildBox {
    private final BoolRelay value;
    private final FrameBox frame;
    private final TextBox text;

    public Selectable(int width, int height, int xOffset, int yOffset, Component parent, String text, BoolRelay value) {
        super(width, height, xOffset, yOffset, parent);
        this.value = value;
        this.frame = new FrameBox(width, height, 0, 0, this, new Vector4f(0, 0, 0, 1), 1);
        this.text = new TextBox(width - 2, height - 2, 1, 1, this, text, false, true);
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        frame.draw(pxScale, matrix4f);
        text.draw(pxScale, matrix4f);

    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        if (contains(event, pxScale) && event.eventType == MouseEvent.EventType.PRESSED) {
            value.setValue(!value.getValue());
            frame.setFrameColor(value.getValue() ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1));
            return true;
        }
        return false;
    }
}
