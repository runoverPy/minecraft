package game.assets.ui_elements.control;

import game.assets.ui_elements.Component;
import game.assets.ui_elements.control.AbstractButton;
import game.util.Cyclic;
import game.util.relay.BoolRelay;
import game.util.relay.EnumRelay;

public class ToggleButton<E extends Enum<E> & Cyclic<E>> extends AbstractButton {
    private final EnumRelay<E> buffer;

    public ToggleButton(int width, int height, int xOffset, int yOffset, Component parent, String name, EnumRelay<E> buffer) {
        this(width, height, xOffset, yOffset, parent, name, buffer, new BoolRelay(true));
    }

    public ToggleButton(int width, int height, int xOffset, int yOffset, Component parent, String name, EnumRelay<E> buffer, BoolRelay isActive) {
        super(width, height, xOffset, yOffset, parent, name, isActive);
        this.buffer = buffer;
    }

    @Override
    public void onAction() {
        buffer.setValue(buffer.getValue().next());
    }
}
