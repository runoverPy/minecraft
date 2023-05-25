import mdk.blocks.BlockFactory;
import mdk.blocks.Chunk;
import mdk.settings.annotation.*;
import mdk.settings.template.ConfigField;
import mdk.settings.template.ConfigFactory;
import mdk.settings.template.ConfigTemplate;
import mdk.settings.template.Template;
import mdk.worldgen.WorldGenerator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

public class NoiseGenerator implements WorldGenerator {
    @Template
    public static void makeTemplate(ConfigTemplate template, ConfigFactory factory) {
        template.add("seed", factory.seedQuery());
    }

    @ConfigField
    public long seed;

    @ConfigField
    // @TextField(query = "Try me if you dare")
    public String desc = "default text";

    @ConfigField
    // @Slider(stop = 10, text = "you're just the wrong type for me")
    public int scale;

    // @Button(text = "select world type")
    @ConfigField
    public Test worldType = Test.WIDE;

    private enum Test {
        FLAT, WIDE, AMPL
    }

    private Random random;

    {
        random = new Random(seed);
        int a = random.nextInt();
    }

    @Override
    public void populateChunk(int cX, int cY, int cZ, Chunk<?> chunk, BlockFactory factory) {

    }

    public static void main(String[] args) {
        for (Field field : NoiseGenerator.class.getDeclaredFields()) {
            System.out.println(Arrays.toString(field.getAnnotations()));
            System.out.println(field);
        }
    }
}
