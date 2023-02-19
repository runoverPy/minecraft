package game.assets.ui_elements;

import game.util.relay.BoolRelay;

class SwitchButton extends AbstractButton {
    private final BoolRelay buffer;

    public SwitchButton(int width, int height, int xOffset, int yOffset, Component parent, String name, BoolRelay buffer) {
        this(width, height, xOffset, yOffset, parent, name, buffer, new BoolRelay(true));
    }

    public SwitchButton(int width, int height, int xOffset, int yOffset, Component parent, String name, BoolRelay buffer, BoolRelay isActive) {
        super(width, height, xOffset, yOffset, parent, name, isActive);
        this.buffer = buffer;
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        if (event.eventType == MouseEvent.EventType.PRESSED && contains(event, pxScale)) {
            buffer.setValue(!buffer.getValue());
            return true;
        }
        return false;
    }
}
