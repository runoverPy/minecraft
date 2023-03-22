package mdk.worldgen;

import org.json.JSONObject;

public class GeneratorConstructor<T extends WorldGenerator> {
    private final Class<T> generatorClass;

    public GeneratorConstructor(Class<T> generatorClass) {
        this.generatorClass = generatorClass;
    }

    public T createGenerator(JSONObject presets) {
        return null;
    }

    public T createGenerator() {
        return null;
    }

    public Object settingsDashboard() {
        return null; // TODO: 25.02.23 implement a menu window here
    }
}
