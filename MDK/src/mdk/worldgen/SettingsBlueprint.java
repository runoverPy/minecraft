package mdk.worldgen;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface SettingsBlueprint {
    Class<?> value();
}
