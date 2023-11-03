import mdk.blocks.Block;
import mdk.blocks.BlockFactory;
import mdk.blocks.Chunk;
import mdk.settings.template.ConfigFactory;
import mdk.settings.template.ConfigField;
import mdk.settings.template.ConfigTemplate;
import mdk.settings.template.Template;
import mdk.worldgen.WorldGenerator;
import noise.NoiseLayers;

public class TestWorldGenerator implements WorldGenerator {
    private static final double FEATURE_SIZE = 48;

    private static final int MAX_SURFACE = 32;

    @Template
    public static void makeTemplate(ConfigTemplate template, ConfigFactory factory) {
        template
          .add("seed", factory.seedQuery())
          .add("featureSize", factory.intSlider(128))
          .add("maxSurface", factory.intSlider(128));
    }

    @ConfigField
    public long seed;

    @ConfigField
    public int featureSize;

    @ConfigField
    public int maxSurface;


    private final NoiseLayers noise;

    {
        noise = new NoiseLayers(System.currentTimeMillis(), 5, FEATURE_SIZE, 0.6, 0.4);
    }

    @Override
    public void populateChunk(int cX, int cY, int cZ, Chunk chunk, BlockFactory factory) {
        if (cZ == 0) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    int height = (int) Math.ceil(noise.eval(i + 16 * cX, j + 16 * cY) * 32) + 16;
                    for (int k = 0; k < Math.min(16, height); k++) {
                        Block block;
                        if (k == height - 1) block = factory.createBlock("vanilla:grass");
                        else if (k > height - 4) block = factory.createBlock("vanilla:dirt");
                        else block = factory.createBlock("vanilla:stone");
                        chunk.setBlock(i, j, k, block);
                    }
                }
            }

        }
        if (cZ == 1) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    int height = (int) Math.ceil(noise.eval(i + 16 * cX, j + 16 * cY) * 32);
                    for (int k = 0; k < Math.min(16, height); k++) {
                        Block block;
                        if (k == height - 1) block = factory.createBlock("vanilla:grass");
                        else if (k > height - 4) block = factory.createBlock("vanilla:dirt");
                        else block = factory.createBlock("vanilla:stone");
                        chunk.setBlock(i, j, k, block);
                    }
                }
            }
        }
        if (cZ == 2) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    int height = (int) Math.ceil(noise.eval(i + 16 * cX, j + 16 * cY) * 32) - 16;
                    for (int k = 0; k < Math.min(16, height); k++) {
                        Block block;
                        if (k == height - 1) block = factory.createBlock("vanilla:grass");
                        else if (k > height - 4) block = factory.createBlock("vanilla:dirt");
                        else block = factory.createBlock("vanilla:stone");
                        chunk.setBlock(i, j, k, block);
                    }
                }
            }
        }
    }
}
