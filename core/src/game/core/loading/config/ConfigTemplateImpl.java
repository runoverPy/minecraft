package game.core.loading.config;

import mdk.settings.template.ConfigField;
import mdk.settings.template.ConfigDetails;
import mdk.settings.template.ConfigTemplate;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Interface for settings templates, the structure defining types and ranges of the fields for a settings context.
 * A instance of this interface is given to a settings context in order to statically determine field ranges and menu elements
 */
public final class ConfigTemplateImpl implements ConfigTemplate {
    private final Map<String, ConfigDetails<?>> fields = new HashMap<>();

    public boolean validate(Class<?> settingsClass) {
        boolean valid = true;
        List<Field> fieldList = Arrays.stream(settingsClass.getDeclaredFields())
                .filter(field -> field.getAnnotation(ConfigField.class) != null)
                .toList();
        for (Field field : fieldList) {
            ConfigField fieldSetting = field.getAnnotation(ConfigField.class);
            String fieldName = fieldSetting.fieldName().equals("") ? field.getName() : fieldSetting.fieldName();
            ConfigDetails<?> fieldData = getFieldData(fieldName);
            if (field.getType() != fieldData.getValueType()) {
                System.err.println("Settings validation failed for field " + field);
                valid = false;
            }
        }
        return valid;
    }

    @Override
    public ConfigTemplate add(String fieldName, ConfigDetails<?> fieldData) {
        if (fieldName == null || fieldData == null) throw new NullPointerException();
        fields.put(fieldName, fieldData);
        return this;
    }

    @Override
    public <T> ConfigTemplate add(String fieldName, ConfigDetails<T> fieldData, T defaultValue) {
        return null;
    }

    public ConfigDetails<?> getFieldData(String fieldName) {
        return fields.get(fieldName);
    }

    public boolean isComplete() {
        return fields.isEmpty(); // TODO: 28.03.23 complete this method
    }
}
