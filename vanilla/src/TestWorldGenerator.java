import mdk.blocks.BlockFactory;
import mdk.blocks.Chunk;
import mdk.worldgen.WorldGenerator;

public class TestWorldGenerator implements WorldGenerator {
    @Override
    public void populateChunk(int cX, int cY, int cZ, Chunk chunk, BlockFactory factory) {
        if (cZ == 0) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    chunk.setBlock(i, j, 0, factory.createBlock("vanilla::stone"));
                }
            }
        }
    }
}
