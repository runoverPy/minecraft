package game.core.settings;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
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
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JSONObject fileContents = (JSONObject) parser.parse(reader);
            reader.close();

            JSONObject settings = new JSONObject();
            settings.put("fov", fov);
            settings.put("dpi", sens);
            if (fileContents.containsKey("minecraft.general")) {
                fileContents.put("minecraft.general", settings);
            } else {
                fileContents = settings;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContents.toJSONString());
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParseException pe) {
            System.err.println("Malformed JSON object");
            pe.printStackTrace(System.err);
        }
    }

    public void load() {
        System.out.println("loading settings");
        try {
            File file = new File(name.toURI());
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JSONObject fileContents = (JSONObject) parser.parse(reader);
            reader.close();

            JSONObject settings;
            if (fileContents.containsKey("minecraft.general")) settings = (JSONObject) fileContents.get("minecraft.general");
            else settings = fileContents;

            this.fov = ((Long) settings.get("fov")).intValue();
            this.sens = ((Double) settings.get("dpi")).floatValue();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } catch (ParseException pe) {
            System.err.println("Malformed JSON object");
            pe.printStackTrace(System.err);
        }
    }
}
