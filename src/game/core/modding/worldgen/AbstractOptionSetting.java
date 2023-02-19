package game.core.modding.worldgen;

import game.util.WorldgenOption;
import game.util.relay.EnumRelay;

abstract class AbstractOptionSetting<T extends Enum<T> & WorldgenOption<T>> extends GeneratorSetting {
    protected EnumRelay<T> currentValue;

    public AbstractOptionSetting(T defaultValue) {
        this.currentValue = new EnumRelay<>(defaultValue);
    }

    public T getValue() {
        return currentValue.getValue();
    }
}
