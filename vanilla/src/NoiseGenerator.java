import mdk.blocks.BlockFactory;
import mdk.blocks.Chunk;
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
        template
          .add("seed", factory.seedQuery())
          .add("desc", factory.inputField("Input Here"), "default text")
          .add("scale", factory.intSlider(64))
          .add("world_type", factory.button(WorldType.class), WorldType.WIDE);
    }

    @ConfigField
    public long seed;

    @ConfigField
    public String desc;

    @ConfigField
    public int scale;

    @ConfigField(fieldName = "world_type")
    public WorldType worldType;

    private enum WorldType {
        FLAT, WIDE, AMPL
    }

    private Random random;

    {
        random = new Random(seed);
        int a = random.nextInt();
    }

    @Override
    public void populateChunk(int cX, int cY, int cZ, Chunk chunk, BlockFactory factory) {

    }

    public static void main(String[] args) {
        for (Field field : NoiseGenerator.class.getDeclaredFields()) {
            System.out.println(Arrays.toString(field.getAnnotations()));
            System.out.println(field);
        }
    }
}
