package mdk.settings;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excludes {
    Class<? extends Annotation>[] value();
}
