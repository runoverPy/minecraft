package mdk.worldgen;

import java.util.function.Function;
import java.util.function.Supplier;

public interface WorldGeneratorLoader {

    <T, U> void register(String generatorName, Supplier<T> presetSupplier, Function<T, U> generatorFactory);

    WorldGeneratorLoader register(String generatorName, Class<? extends WorldGenerator> generatorClass);

    // todo add additional builder for generators
    // generator().setName(/* name */).setImage(/* image */).submit();
}
