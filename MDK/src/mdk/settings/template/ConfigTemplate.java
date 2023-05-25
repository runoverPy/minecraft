package mdk.settings.template;

public interface ConfigTemplate {
    ConfigTemplate add(String fieldName, ConfigDetails<?> fieldData);
    <T> ConfigTemplate add(String fieldName, ConfigDetails<T> fieldData, T defaultValue);
}
