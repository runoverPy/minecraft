package mdk.worldgen;

import mdk.structure.Element;
import mdk.structure.ElementFactory;

public abstract class GeneratorSetting <T> {
    private T storedValue;

    GeneratorSetting() {}

    public abstract Element widget(ElementFactory elementFactory);
}