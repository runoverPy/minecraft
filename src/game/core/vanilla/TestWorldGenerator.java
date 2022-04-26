package game.core.vanilla;

import game.core.modding.WorldGenerator;
import game.mechanics.blocks.Block;
import game.core.server.Chunk;

public class TestWorldGenerator implements WorldGenerator {
    @Override
    public Chunk generateChunk(int cX, int cY, int cZ) {
        Chunk chunk = new Chunk();
        if (cZ == 0) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    chunk.setBlock(i, j, 0, new Block("vanilla::stone"));
                }
            }
        }
        return chunk;
    }
}
