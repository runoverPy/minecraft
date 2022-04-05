package game.core.settings;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

public class SettingsHandler {
    public SettingsHandler(String path) throws IOException, ParseException {
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) throw new FileNotFoundException();

        JSONParser parser = new JSONParser();
        JSONObject storedSettings = (JSONObject) parser.parse(new InputStreamReader(stream));

        for (Field field : getClass().getFields()) {
            if (field.isAnnotationPresent(Setting.class)) {
                String name = field.getName();
                try {
                    Integer.class.cast(null);
                    field.set(this, new SettingField<>(storedSettings.get(name)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public final String toString() {
        StringBuilder out = new StringBuilder();
        out.append(getClass().getName()).append(" {");
        for (Field field : getClass().getFields()) {
            if (field.isAnnotationPresent(Setting.class)) {
                String name = field.getName();
                try {
                    Object value = field.get(this);
                    out.append("\n\t").append(name).append(": ").append(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return out.append("\n}").toString();
    }

    public Object getSetting(String setting) throws NoSuchFieldException, IllegalAccessException {
        return getClass().getField(setting).get(this);
    }

    public void setSetting(String setting, Object value) throws NoSuchFieldException, IllegalAccessException {
        getClass().getField(setting).set(this, value);
    }
}
