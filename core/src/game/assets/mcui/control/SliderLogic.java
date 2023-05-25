package game.assets.mcui.control;

import game.assets.event.MouseEvent;
import game.assets.event.dispatch.InputMap;

public class SliderLogic {
    private final InputMap<Slider> inputMap;
    private final Slider host;

    public SliderLogic(Slider host) {
        this.host = host;
        this.inputMap = new InputMap<>(host);

        inputMap.addEventMapping(
                new InputMap.MouseMapping(MouseEvent.MOUSE_PRESSED, event -> pressed()),
                new InputMap.MouseMapping(MouseEvent.MOUSE_RELEASED, event -> released())
        );
    }

    private void pressed() {}
    private void released() {}
}
