package game.assets.mcui.control;

import game.assets.event.dispatch.InputMap;
import game.assets.event.dispatch.InputMap.MouseMapping;
import game.assets.event.MouseEvent;

public class ButtonLogic {
    private final InputMap<AbstractButton> inputMap;
    private final AbstractButton button;

    public ButtonLogic(AbstractButton host) {
        inputMap = new InputMap<>(host);
        button = host;

        inputMap.addEventMapping(
                new MouseMapping(MouseEvent.MOUSE_PRESSED, this::pressed),
                new MouseMapping(MouseEvent.MOUSE_RELEASED, this::released)
        );
    }

    private void pressed(MouseEvent e) {

    }

    private void released(MouseEvent e) {
        button.fire();
    }
}
