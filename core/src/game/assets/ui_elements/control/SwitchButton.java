package game.assets.ui_elements.control;

import game.assets.ui_elements.Component;
import game.assets.ui_elements.control.AbstractButton;
import game.util.relay.BoolRelay;

public class SwitchButton extends AbstractButton {
    private final BoolRelay buffer;

    public SwitchButton(int width, int height, int xOffset, int yOffset, Component parent, String name, BoolRelay buffer) {
        this(width, height, xOffset, yOffset, parent, name, buffer, new BoolRelay(true));
    }

    public SwitchButton(int width, int height, int xOffset, int yOffset, Component parent, String name, BoolRelay buffer, BoolRelay isActive) {
        super(width, height, xOffset, yOffset, parent, name, isActive);
        this.buffer = buffer;
    }

    @Override
    public void onAction() {
        buffer.setValue(!buffer.getValue());
    }
}
