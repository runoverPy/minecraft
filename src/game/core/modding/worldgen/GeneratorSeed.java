package game.core.modding.worldgen;

import game.assets.ui_elements.Widget;

import java.util.Random;

public class GeneratorSeed extends GeneratorSetting {
    private final StringBuffer buffer;

    public GeneratorSeed() {
        this.buffer = new StringBuffer();
    }

    @Override
    public Widget widget() {
        return Widget.query(buffer);
    }

    public long getSeed() throws NumberFormatException {
        String value = buffer.toString();
        long seed;
        if (value.equals("")) seed = new Random().nextLong(); // rng
        else seed = Long.parseLong(value);
        return seed;
    }
}
