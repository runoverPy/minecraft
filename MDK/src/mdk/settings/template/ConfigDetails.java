package mdk.settings.template;

public interface ConfigDetails<T> {
    Class<T> getValueType();

    void setName(String name);

    String getName();

    void setValue(T value);

    T getValue();
}
