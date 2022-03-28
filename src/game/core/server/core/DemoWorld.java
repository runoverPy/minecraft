package game.core.server.core;

import game.mechanics.blocks.Block;

public class DemoWorld extends World {
    public DemoWorld() {
        super();
        System.out.println("World initialization complete");
    }

    @Override
    protected Chunk loadChunk(int cX, int cY, int cZ) {
        Chunk chunk = new Chunk();
        if (cZ == 0) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    chunk.setBlock(i, j, 0, new Block("vanilla::bricks"));
                }
            }
        }
        return chunk;
    }

    @Override
    protected void unloadChunk(int cX, int cY, int cZ, Chunk chunk) {

    }

}
