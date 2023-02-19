package game.core.modding.worldgen;

import game.assets.ui_elements.Widget;
import game.util.WorldgenOption;

class DropDownOptionSetting<T extends Enum<T> & WorldgenOption<T>> extends AbstractOptionSetting<T> {
    public DropDownOptionSetting(T defaultValue) {
        super(defaultValue);
        throw new UnsupportedOperationException();
    }

    @Override
    public Widget widget() {
        return null;
    }
}
