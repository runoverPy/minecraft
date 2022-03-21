package game.core.settings;

import java.lang.reflect.Field;

public abstract class SettingsHandler {
    public void SettingsHandler(String settingsSpace) {}

    public SettingField getSetting(String name) throws NoSuchFieldException {
        try {
            return (SettingField) getClass().getDeclaredField(name).get(name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SettingsHandler loadSettings() {
        for (Field field : getClass().getDeclaredFields()) {
            field.getType().getDeclaredMethods();
        }
        return null;
    }
}
