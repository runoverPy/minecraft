package mdk.worldgen;

import mdk.structure.Element;
import mdk.structure.ElementFactory;

public abstract class GeneratorSetting <T> {
    private T storedValue;

    public abstract Element widget(ElementFactory elementFactory);
}