package game.core.settings;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;

public class GeneralSettings extends Settings {
    protected final URL saveFile;

    @Setting
    private int fov;
    @Setting
    private float dpi;

    public GeneralSettings() throws FileNotFoundException {
        super("/dat/settings.json", "minecraft.general");
        this.saveFile = getClass().getResource("/dat/settings.json");
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
        System.out.println("saving settings");
        try {
            File file = new File(saveFile.getPath());
            BufferedReader reader = new BufferedReader(new FileReader(file));
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

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContents.toString());
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void load() {
        System.out.println("loading settings");
        try {
            File file = new File(saveFile.getPath());
            BufferedReader reader = new BufferedReader(new FileReader(file));
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
