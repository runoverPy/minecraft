package game.assets.ui_elements.control;

import game.assets.ui_elements.Component;
import game.util.relay.BoolRelay;

public class Selectable extends AbstractButton {
    private final BoolRelay value;

    public Selectable(int width, int height, int xOffset, int yOffset, Component parent, String text, BoolRelay value) {
        super(width, height, xOffset, yOffset, parent, text, () -> true);
        this.value = value;
    }

    @Override
    public void onAction() {
        value.setValue(!value.getValue());
        System.out.println("Toggling value to " + value.get());
//        frame.setFrameColor(value.getValue() ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1));
    }
}
