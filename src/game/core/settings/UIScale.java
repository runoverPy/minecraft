package game.core.settings;

import game.util.Cyclic;

public enum UIScale implements Cyclic<UIScale> {
    SMALL, MEDIUM, LARGE, XLARGE;

    @Override
    public UIScale next() {
        return UIScale.values()[(ordinal() + 1) % values().length];
    }
}
