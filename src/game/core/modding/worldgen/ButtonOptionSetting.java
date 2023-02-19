package game.core.modding.worldgen;

import game.assets.ui_elements.Widget;
import game.util.WorldgenOption;

public class ButtonOptionSetting<E extends Enum<E> & WorldgenOption<E>> extends AbstractOptionSetting<E> {
    private final String name;

    public ButtonOptionSetting(String name, E defaultValue) {
        super(defaultValue);
        this.name = name;
    }

    @Override
    public Widget widget() {
        return Widget.button(name, currentValue);
    }
}
