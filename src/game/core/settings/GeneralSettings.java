package game.core.settings;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class GeneralSettings {
    protected final URL name;

    private int fov;
    private float sens;

    public GeneralSettings() {
        this.name = getClass().getResource("/dat/settings.json");
        load();
    }

    public int getFOV() {
        return fov;
    }

    public void setFOV(int value) {
        fov = value;
    }

    public float getDPI() {
        return sens;
    }

    public void setDPI(float value) {
        sens = value;
    }

    public void save() {
        System.out.println("saving settings");
        try {
            File file = new File(name.getPath());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JSONObject fileContents = new JSONObject(reader);
            reader.close();

            JSONObject settings = new JSONObject();
            settings.put("fov", fov);
            settings.put("dpi", sens);
            if (fileContents.has("minecraft.general")) {
                fileContents.put("minecraft.general", settings);
            } else {
                fileContents = settings;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContents.toString());
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (JSONException pe) {
            System.err.println("Malformed JSON object");
            pe.printStackTrace(System.err);
        }
    }

    public void load() {
        System.out.println("loading settings");
        try {
            File file = new File(name.toURI());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JSONObject fileContents = new JSONObject(reader);
            reader.close();

            JSONObject settings;
            if (fileContents.has("minecraft.general")) settings = fileContents.getJSONObject("minecraft.general");
            else settings = fileContents;

            this.fov = settings.getInt("fov");
            this.sens = settings.getFloat("dpi");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } catch (JSONException pe) {
            System.err.println("Malformed JSON object");
            pe.printStackTrace(System.err);
        }
    }
}
