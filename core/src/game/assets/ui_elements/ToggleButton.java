package game.assets.ui_elements;

import game.util.Cyclic;
import game.util.relay.BoolRelay;
import game.util.relay.EnumRelay;

class ToggleButton<E extends Enum<E> & Cyclic<E>> extends AbstractButton {
    private final EnumRelay<E> buffer;

    public ToggleButton(int width, int height, int xOffset, int yOffset, Component parent, String name, EnumRelay<E> buffer) {
        this(width, height, xOffset, yOffset, parent, name, buffer, new BoolRelay(true));
    }

    public ToggleButton(int width, int height, int xOffset, int yOffset, Component parent, String name, EnumRelay<E> buffer, BoolRelay isActive) {
        super(width, height, xOffset, yOffset, parent, name, isActive);
        this.buffer = buffer;
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        if (event.eventType == MouseEvent.EventType.RELEASED && contains(event, pxScale)) {
            buffer.setValue(buffer.getValue().next());
            return true;
        }
        return false;
    }
}
