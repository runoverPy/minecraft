package mdk.settings.template;

import mdk.settings.Excludes;
import mdk.settings.annotation.Button;
import mdk.settings.annotation.RandomSeed;
import mdk.settings.annotation.Slider;
import mdk.settings.annotation.TextField;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Excludes({Button.class, RandomSeed.class, Slider.class, TextField.class})
public @interface ConfigField {
    String fieldName() default "";
}
