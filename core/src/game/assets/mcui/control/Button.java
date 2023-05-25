package game.assets.mcui.control;

import game.assets.event.ActionEvent;

import java.util.function.Supplier;

public class Button extends AbstractButton {
    public Button(String desc, Supplier<Boolean> activeCondition) {
        super(desc, activeCondition);
    }

    public Button(String desc) {
        this(desc, TRUE);
    }

    @Override
    public void fire() {
        fireEvent(new ActionEvent());
    }
}
