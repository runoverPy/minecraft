package game.core.settings;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class GeneralSettings extends Settings {
    @Setting
    private int fov;
    @Setting
    private float dpi;

    public GeneralSettings() throws FileNotFoundException {
        super("/dat/settings.json", "minecraft.general");
        load();
    }

    public int getFOV() {
        return fov;
    }

    public void setFOV(int value) {
        fov = value;
    }

    public float getDPI() {
        return dpi;
    }

    public void setDPI(float value) {
        dpi = value;
    }

    public void save() {
        try {
            BufferedReader reader = Files.newBufferedReader(getSavePath());
            JSONObject fileContents = new JSONObject(new JSONTokener(reader));
            reader.close();

            JSONObject settings = new JSONObject();

            settings.put("fov", fov);
            settings.put("dpi", dpi);

            if (fileContents.has("minecraft.general")) {
                fileContents.put("minecraft.general", settings);
            } else {
                fileContents = settings;
            }

            BufferedWriter writer = Files.newBufferedWriter(getSavePath());
            writer.write(fileContents.toString());
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void load() {
        try {
            BufferedReader reader = Files.newBufferedReader(getSavePath());
            JSONObject fileContents = new JSONObject(new JSONTokener(reader));
            reader.close();

            JSONObject settings;
            if (fileContents.has("minecraft.general"))
                settings = fileContents.getJSONObject("minecraft.general");
            else settings = fileContents;

            fov = settings.getInt("fov");
            dpi = settings.getFloat("dpi");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "GeneralSettings {\n" +
          "\tfov: " + fov + ",\n" +
          "\tdpi: " + dpi + "\n" +
          '}';
    }
}
