package game.assets.mcui.control;

import game.util.Cyclic;
import game.util.relay.EnumRelay;

import java.util.function.Supplier;

public class SelectButton<E extends Enum<E> & Cyclic<E>> extends AbstractButton {
    private final EnumRelay<E> relay;

    protected SelectButton(String desc, Supplier<Boolean> activeCondition, E initValue) {
        super(desc, activeCondition);
        this.relay = new EnumRelay<>(initValue);
    }

    public EnumRelay<E> getRelay() {
        return relay;
    }

    public E getValue() {
        return relay.getValue();
    }

    public void setValue(E value) {
        relay.setValue(value);
    }

    @Override
    public void fire() {
        relay.setValue(relay.getValue().next());
    }
}
