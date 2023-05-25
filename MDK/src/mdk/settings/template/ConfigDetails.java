package mdk.settings.template;

public interface ConfigDetails<T> {
    T getValue();
    Class<T> getValueType();
}
