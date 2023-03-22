package mdk.worldgen;

import mdk.worldgen.WorldGenerator;

import java.util.function.Function;
import java.util.function.Supplier;

public interface WorldGeneratorLoader {
    <T, U> void register(String generatorName, Supplier<T> presetSupplier, Function<T, U> generatorFactory);

    void register(String generatorName, Function<Long, WorldGenerator> generator);

    void register(String generatorName, Class<? extends WorldGenerator> generatorClass);
}
