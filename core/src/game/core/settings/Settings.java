package game.core.settings;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Settings {
    private final URL saveFile;

    public Settings(String path, String name) throws FileNotFoundException {
        saveFile = getClass().getResource("/dat/settings.json");
        if (saveFile == null) throw new FileNotFoundException(path);
    }

    public void save() {
        try {
            File file = new File(saveFile.getPath());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JSONObject fileContents = new JSONObject(new JSONTokener(reader));
            reader.close();

            JSONObject settings = new JSONObject();
            fileContents.put("minecraft.general", settings);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContents.toString());
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void load() {
        try {
            File file = new File(saveFile.getPath());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JSONObject fileContents = new JSONObject(new JSONTokener(reader));
            reader.close();

            JSONObject settings = fileContents.getJSONObject("minecraft.general");

            List<Field> fields = Arrays.stream(
                    getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Setting.class)
            ).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Type {
        INT, FLOAT, LONG, DOUBLE, BOOL, ENUM;
    }
}
