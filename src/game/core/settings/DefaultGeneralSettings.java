package game.core.settings;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;

public final class DefaultGeneralSettings extends GeneralSettings {
    @Override
    public void save() {}

    @Override
    public void load() {
        try {
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dat/settings.json")));
            JSONObject fileContents = (JSONObject) parser.parse(reader);
            reader.close();

            JSONObject settings;
            if (fileContents.containsKey("minecraft.general")) settings = (JSONObject) fileContents.get("minecraft.general");
            else settings = fileContents;

            setFOV(((Long) settings.get("fov")).intValue());
            setDPI(((Double) settings.get("dpi")).floatValue());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException pe) {
            System.err.println("Malformed JSON object");
            pe.printStackTrace(System.err);
        }
    }
}
