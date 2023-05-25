package mdk.settings.annotation;

import mdk.settings.Excludes;
import mdk.settings.template.ConfigField;
import mdk.settings.template.Template;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SettingTypes({boolean.class, Enum.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Excludes({RandomSeed.class, Slider.class, TextField.class, ConfigField.class})
public @interface Button {
    String text();
}
