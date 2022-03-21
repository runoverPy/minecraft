package game.util;

import game.core.settings.UIScale;

public interface Cyclic<E extends Enum<E>> {
    E next();
}

