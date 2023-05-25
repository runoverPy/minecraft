package mdk.settings.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@SupportedAnnotationTypes({
        "mdk.settings.annotation.Button",
        "mdk.settings.annotation.RandomSeed",
        "mdk.settings.annotation.Slider",
        "mdk.settings.annotation.TextField"
})
@SupportedSourceVersion(SourceVersion.RELEASE_16)
public class AnnotationSettingProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            SettingTypes settingTypesAnnotation = annotation.getAnnotation(SettingTypes.class);
            List<? extends TypeMirror> settingsTypes = getValueArray(settingTypesAnnotation, SettingTypes::value);
            assert settingsTypes != null;
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elements) {
                TypeMirror fieldType = element.asType();
                boolean valid = settingsTypes.stream()
                        .anyMatch(allowedType -> processingEnv.getTypeUtils().isSubtype(fieldType, allowedType));
                processingEnv.getMessager().printMessage(
                        valid ? Diagnostic.Kind.NOTE : Diagnostic.Kind.ERROR,
                        new StringBuilder()
                                .append(element).append("\n")
                                .append(fieldType).append(" & ").append(settingsTypes).append("\n"),
                        element
                );
            }
        }
        return false;
    }

    private static <T extends Annotation> TypeMirror getValue(T annotation, Function<T, Class<?>> fieldGetter) {
        try {
            fieldGetter.apply(annotation);
        } catch (MirroredTypeException mte) {
            return mte.getTypeMirror();
        }
        return null;
    }

    private static <T extends Annotation> List<? extends TypeMirror> getValueArray(T annotation, Function<T, Class<?>[]> fieldGetter) {
        try {
            fieldGetter.apply(annotation);
        } catch (MirroredTypesException mte) {
            return mte.getTypeMirrors();
        }
        return null;
    }
}
