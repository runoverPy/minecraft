package game.core.loading.config;

import game.assets.mcui.Component;
import mdk.settings.template.ConfigDetails;

public abstract class AbstractConfigDetails<T> implements ConfigDetails<T> {
    private String name;
    private T value;

    private final Class<T> valueClass;

    protected AbstractConfigDetails(Class<T> valueClass) {
        this.valueClass = valueClass;
    }

    @Override
    public Class<T> getValueType() {
        return valueClass;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setValue(T value) {
        this.value = value;
    }
    @Override
    public T getValue() {
        return value;
    }

    public abstract Component getUIElement();
}
