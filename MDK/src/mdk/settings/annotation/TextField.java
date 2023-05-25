package mdk.settings.annotation;

import mdk.settings.Excludes;
import mdk.settings.template.ConfigDetails;
import mdk.settings.template.ConfigField;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SettingTypes({int.class, float.class, long.class, double.class, String.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Excludes({Button.class, RandomSeed.class, Slider.class, ConfigField.class})
public @interface TextField {
    String query();
}
