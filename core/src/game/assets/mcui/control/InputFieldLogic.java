package game.assets.mcui.control;

import game.assets.event.KeyEvent;
import game.assets.event.MouseEvent;
import game.assets.event.dispatch.InputMap;

public class InputFieldLogic {
    private final InputMap<InputField> inputMap;
    private final InputField host;

    public InputFieldLogic(InputField host) {
        inputMap = new InputMap<>(host);
        this.host = host;

        inputMap.addEventMapping(
                new InputMap.MouseMapping(MouseEvent.MOUSE_RELEASED, event -> host.toggleSelect()),
                new InputMap.KeyMapping(KeyEvent.KEY_RELEASED, null),
                new InputMap.KeyMapping(KeyEvent.KEY_REPEAT, null)
        );
    }
}
