package game.core.settings;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public abstract class Settings {
    private final Path save;

    public Settings(String path, String name) throws FileNotFoundException {
        save = Paths.get("core/dat/settings.json");
    }

    public Path getSavePath() {
        return save;
    }

    public void save() {
        try {
            BufferedReader reader = Files.newBufferedReader(save);
            JSONObject fileContents = new JSONObject(new JSONTokener(reader));
            reader.close();

            JSONObject settings = new JSONObject();
            fileContents.put("minecraft.general", settings);

            BufferedWriter writer = Files.newBufferedWriter(save);
            writer.write(fileContents.toString());
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void load() {
        try {
            BufferedReader reader = Files.newBufferedReader(save);
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
