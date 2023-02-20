package game.core.settings;

import org.json.JSONObject;
import org.json.JSONTokener;

public final class DefaultGeneralSettings extends GeneralSettings {
    @Override
    public void save() {}

    @Override
    public void load() {
        JSONTokener tokenizer = new JSONTokener(getClass().getResourceAsStream("/dat/settings.json"));
        JSONObject fileContents = new JSONObject(tokenizer);

        JSONObject settings;
        if (fileContents.has("minecraft.general")) settings = fileContents.getJSONObject("minecraft.general");
        else settings = fileContents;

        setFOV(settings.getInt("fov"));
        setDPI(settings.getFloat("dpi"));
    }
}
