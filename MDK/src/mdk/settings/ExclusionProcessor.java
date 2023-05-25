package mdk.settings;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("mdk.settings.Excludes")
@SupportedSourceVersion(SourceVersion.RELEASE_16)
public class ExclusionProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER, annotations.toString());
        TypeElement excludesAnnotation = annotations.stream().findFirst().orElseThrow();
        TypeElement[] exclusiveElements = roundEnv.getElementsAnnotatedWith(Excludes.class)
                .stream().map(element -> (TypeElement)element).toArray(TypeElement[]::new);
        Set<? extends Element> exclusivelyAnnotatedElements = roundEnv.getElementsAnnotatedWithAny(exclusiveElements);
        for (Element exclusivelyAnnotatedElement : exclusivelyAnnotatedElements) {
            List<? extends AnnotationMirror> markedAnnotations = exclusivelyAnnotatedElement.getAnnotationMirrors()
                    .stream()
                    .filter(annotation -> annotation.getAnnotationType()
                            .getAnnotationMirrors()
                            .contains((AnnotationMirror) excludesAnnotation)) // TODO: 27.03.23 throws ClassCastException
                    .toList();
            if (markedAnnotations.isEmpty()) continue; // to be thorough
            Set<TypeMirror> excludedTypes = new HashSet<>();

            boolean intersects = false;
            for (AnnotationMirror markedAnnotation : markedAnnotations) {
                Set<TypeMirror> excludedTypesLocal = new HashSet<>(getValueArray(
                        markedAnnotation.getAnnotationType().getAnnotation(Excludes.class),
                        Excludes::value
                ));
                if (excludedTypes.stream().anyMatch(excludedTypesLocal::contains)) {
                    intersects = true;
                }
                excludedTypes.addAll(excludedTypesLocal);
            }
            if (intersects) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Field is annotated by two or more exclusive annotations",
                        exclusivelyAnnotatedElement
                );
            } else {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.NOTE,
                        "Properly exclusively annotated!",
                        exclusivelyAnnotatedElement
                );
            }
        }
        return false;
    }

    private static <T extends Annotation> List<? extends TypeMirror> getValueArray(T annotation, Function<T, Class<?>[]> fieldGetter) {
        try {
            fieldGetter.apply(annotation);
        } catch (MirroredTypesException mte) {
            return mte.getTypeMirrors();
        }
        throw new RuntimeException();
    }
}
