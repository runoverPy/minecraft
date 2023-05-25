package game.core.loading;

import game.core.loading.config.ConfigFactoryImpl;
import game.core.loading.config.ConfigTemplateImpl;
import mdk.settings.template.ConfigDetails;
import mdk.settings.template.ConfigFactory;
import mdk.settings.template.ConfigTemplate;
import mdk.worldgen.WorldGenerator;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Uses Reflection to create new instances of a world generator.
 * The generator settings fields are specified in the generator implementations.
 */
public class GeneratorBuilder {
    public static final ConfigFactory factory = new ConfigFactoryImpl();
    private final Class<? extends WorldGenerator> generatorClass;
    private ConfigTemplateImpl template; // TODO: 27.03.23 move this to a class loading config classes

    public GeneratorBuilder(Class<? extends WorldGenerator> generatorClass) {
        this.generatorClass = generatorClass;
        template = getGeneratorTemplate();
    }

    private ConfigTemplateImpl getGeneratorTemplate() {
        ConfigTemplateImpl template = new ConfigTemplateImpl();

        try {
            Method makeTemplate = this.generatorClass.getMethod("makeTemplate", ConfigTemplateImpl.class, ConfigFactory.class);
            makeTemplate.invoke(null, template, factory);
            if (!template.validate(generatorClass)) {
                System.err.println("Template for " + generatorClass + " failed to validate");
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException ignored) {}

//        for (Field generatorField : generatorClass.getDeclaredFields()) {
//            Class<?> fieldType = generatorField.getType();
//            Set<Class<? extends Annotation>> excludedAnnotations = new HashSet<>(), presentAnnotations = new HashSet<>();
//            for (Annotation appliedAnnotation : generatorField.getAnnotations()) {
//                Class<? extends Annotation> annoType = appliedAnnotation.annotationType();
//                presentAnnotations.add(annoType);
//                if (annoType.isAnnotationPresent(Excludes.class)) {
//                    excludedAnnotations.addAll(Set.of(annoType.getAnnotation(Excludes.class).value()));
//                }
//                if (annoType.isAnnotationPresent(SettingTypes.class)) {
//                    Set<Class<?>> permittedTypes = Set.of(annoType.getAnnotation(SettingTypes.class).value());
//                    if (permittedTypes.contains(fieldType)) {
//                        template.add(generatorField.getName(), createConfigDetails(appliedAnnotation, fieldType));
//                    } else {
//                        System.err.println("Field " + generatorField + " has the wrong type for Annotation " + annoType);
//                    }
//                }
//            }
//            Set<Class<? extends Annotation>> problematicAnnotations = new HashSet<>(presentAnnotations);
//            problematicAnnotations.retainAll(excludedAnnotations);
//            if (!problematicAnnotations.isEmpty())
//                System.err.println("Field " + generatorField + " has mutually exclusive Annotations " + problematicAnnotations);
//        }

        // load templates from annotations
        return template;
    }

    private <T> ConfigDetails<T> createConfigDetails(Annotation annotation, Class<T> targetType) {
        return null;
    }

    public Optional<WorldGenerator> getGenerator() {
        if (isReady()) {
            try {
                return Optional.of(generatorClass.getDeclaredConstructor().newInstance());
                // populate generator with settings
            } catch (ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        } else return Optional.empty();
    }

    public WorldGenerator getGenerator(JSONObject presets) {
        return null;
    }

    public boolean isReady() {
        return template.isComplete();
    }
}
