package game.assets.mcui.control;

import game.util.relay.BoolRelay;

import java.util.function.Supplier;

public class Switch extends AbstractButton {
    private final BoolRelay relay;

    public Switch(String desc, Supplier<Boolean> activeCondition) {
        super(desc, activeCondition);
        this.relay = new BoolRelay();
    }

    public BoolRelay getRelay() {
        return relay;
    }

    public boolean getValue() {
        return relay.getValue();
    }

    public void setValue(boolean value) {
        relay.setValue(value);
    }

    @Override
    public void fire() {
        relay.setValue(!relay.getValue());
    }
}
