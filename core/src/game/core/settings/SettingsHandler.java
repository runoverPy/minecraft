package game.core.settings;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class SettingsHandler {
    private final Map<String, Settings> settingsPages = new HashMap<>();

    public SettingsHandler() throws FileNotFoundException {
        settingsPages.put("core.general", new GeneralSettings());
        // load core settings
        // load mod settings
    }

    public <T> T getSetting(String setting) throws NoSuchFieldException, IllegalAccessException, ClassCastException {
        return (T) getClass().getField(setting).get(this);
    }

    public int getIntSetting(String setting) {
        return 0;
    }
}
