package mdk.worldgen;

import mdk.structure.Element;
import mdk.structure.ElementFactory;

import java.util.Random;

public class GeneratorSeed extends GeneratorSetting<Long> {
    private final StringBuffer buffer;

    public GeneratorSeed() {
        this.buffer = new StringBuffer();
    }

    @Override
    public Element widget(ElementFactory factory) {
        return factory.query(buffer);
    }

    public long getSeed() throws NumberFormatException {
        String value = buffer.toString();
        long seed;
        if (value.equals("")) seed = new Random().nextLong(); // rng
        else seed = Long.parseLong(value);
        return seed;
    }
}
