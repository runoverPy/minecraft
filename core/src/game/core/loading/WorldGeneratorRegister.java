package game.core.loading;

import mdk.worldgen.WorldGenerator;
import mdk.worldgen.WorldGeneratorLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class WorldGeneratorRegister implements WorldGeneratorLoader {
    @Deprecated
    private final Map<String, Class<? extends WorldGenerator>> generatorClasses;

    public WorldGeneratorRegister() {
        generatorClasses = new HashMap<>();
    }

    public <T, U> void register(String generatorName, Supplier<T> presetSupplier, Function<T, U> generatorFactory) {
        throw new UnsupportedOperationException();
    }

    public GeneratorBuilder getGeneratorBuilder(String generator) {
        return new GeneratorBuilder(generatorClasses.get(generator));
    }

    public boolean isGeneratorRegistered(String generator) {
        return generatorClasses.containsKey(generator);
    }

    @Deprecated
    public WorldGenerator getGenerator(String name) {
        if (!generatorClasses.containsKey(name)) throw new RegistrationException("No generator registered under given name");
        return new GeneratorBuilder(generatorClasses.get(name)).getGenerator().orElseThrow();
    }

    @Override
    @Deprecated
    public WorldGeneratorLoader register(String generatorName, Class<? extends WorldGenerator> generatorClass) {
        if (generatorClasses.containsKey(generatorName)) throw new RegistrationException("Generator registered with same name");
        generatorClasses.put(generatorName, generatorClass);
        return this;
    }

    @Override
    public GeneratorTemplateBuilder generator() {
        return new GeneratorTemplateBuilder() {
            private String name;
            private Class<? extends WorldGenerator> generatorClass;
            private Supplier<? extends WorldGenerator> factory;

            @Override
            public GeneratorTemplateBuilder setName(String name) {
                this.name = name;
                return this;
            }

            @Override
            public GeneratorTemplateBuilder setImage(Object image) {
                throw new UnsupportedOperationException();
            }

            @Override
            public GeneratorTemplateBuilder setClass(Class<? extends WorldGenerator> generatorClass) {
                this.generatorClass = generatorClass;
                return this;
            }

            public GeneratorTemplateBuilder setFactory(Supplier<? extends WorldGenerator> factory) {
                this.factory = factory;
                return this;
            }

            @Override
            public void submit() {
                if (name == null) throw new IllegalStateException("a name must be supplied to the template builder before submitting");
                if (generatorClass == null) throw new IllegalStateException("a world generator class must be assigned to the template builder before submitting");
                register(name, generatorClass);
            }
        };
    }

    public String[] getLoadedGenerators() {
        return generatorClasses.keySet().toArray(String[]::new);
    }
}
