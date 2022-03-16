package game.core.vanilla;

import game.mechanics.WorldGenerator;
import game.mechanics.blocks.Block;
import game.core.server.core.Chunk;

public class TestWorldGenerator extends WorldGenerator {
    @Override
    public Chunk generateChunk(int x, int y, int z) {
        Chunk chunk = new Chunk();
        if (z == 0) {
            for (int bX = 0; bX < 16; bX++) {
                for (int bY = 0; bY < 16; bY++) {
                    chunk.setBlock(
                            16 * x + bX,
                            16 * y + bY,
                            0,
                            new Block("vanilla::bricks"));
                }
            }
        }
        return chunk;
    }
}
