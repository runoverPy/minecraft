package mdk;

import mdk.settings.annotation.SettingTypes;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.util.SimpleTypeVisitor14;
import javax.tools.Diagnostic;
import java.util.Arrays;
import java.util.Set;

@SupportedAnnotationTypes("mdk.Block")
@SupportedSourceVersion(SourceVersion.RELEASE_16)
public class GeneratorAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER, "we're in");
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elements) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, element + " is annotated with Block!");
            }
        }
        return false;
    }
}
