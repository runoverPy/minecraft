package game.core.loading;

import mdk.worldgen.WorldGenerator;
import mdk.worldgen.WorldGeneratorLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class WorldGeneratorRegister implements WorldGeneratorLoader {
    private final Map<String, Function<Long, WorldGenerator>> registeredGenerators;

    public WorldGeneratorRegister() {
        registeredGenerators = new HashMap<>();
    }

    public <T, U> void register(String generatorName, Supplier<T> presetSupplier, Function<T, U> generatorFactory) {}

    public void register(String generatorName, Function<Long, WorldGenerator> generator) {
        if (registeredGenerators.containsKey(generatorName)) throw new RegistrationException("Generator registered with same name");
        registeredGenerators.put(generatorName, generator);
    }

    public WorldGenerator getGenerator(String name, long seed) {
        if (!registeredGenerators.containsKey(name)) throw new RegistrationException("No generator registered under given name");
        return registeredGenerators.get(name).apply(seed);
    }

    public void register(String generatorName, Class<? extends WorldGenerator> generatorClass) {

    }
}
