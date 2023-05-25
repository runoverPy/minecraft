package game.core.loading;

import mdk.worldgen.WorldGenerator;
import mdk.worldgen.WorldGeneratorLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class WorldGeneratorRegister implements WorldGeneratorLoader {
    private final Map<String, Class<? extends WorldGenerator>> generatorClasses;

    public WorldGeneratorRegister() {
        generatorClasses = new HashMap<>();
    }

    public <T, U> void register(String generatorName, Supplier<T> presetSupplier, Function<T, U> generatorFactory) {
        throw new RuntimeException();
    }

    @Deprecated
    public WorldGenerator getGenerator(String name) {
        if (!generatorClasses.containsKey(name)) throw new RegistrationException("No generator registered under given name");
        return new GeneratorBuilder(generatorClasses.get(name)).getGenerator().orElseThrow();
    }

    @Override
    public WorldGeneratorLoader register(String generatorName, Class<? extends WorldGenerator> generatorClass) {
        System.out.println("registering generator class " + generatorName);
        if (generatorClasses.containsKey(generatorName)) throw new RegistrationException("Generator registered with same name");
        generatorClasses.put(generatorName, generatorClass);
        return this;
    }

    public boolean isGeneratorRegistered(String generatorName) {
        return generatorClasses.containsKey(generatorName);
    }

    public String[] getLoadedGenerators() {
        return generatorClasses.keySet().toArray(String[]::new);
    }
}
