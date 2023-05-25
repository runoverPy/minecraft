package game.core.loading.config;

import mdk.settings.template.ConfigDetails;

public abstract class AbstractConfigDetails<T> implements ConfigDetails<T> {
    public abstract void set(T value);
}
