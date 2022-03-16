package game.core.server.core;

import game.mechanics.blocks.Block;

public class DemoWorld extends World {
    public DemoWorld() {
        super();
        initTestWorld();
        System.out.println("World initialization complete");
    }

    private void initTestWorld() {
        for (int i = -32; i < 32; i++) {
            for (int j = -32; j < 32; j++) {
                setBlock(i, j, 0, new Block("vanilla::bricks"));
            }
        }

        for (int i = 4; i < 12; i++) {
            for (int j = 4; j < 12; j++) {
                setBlock(i, j, 4, new Block("vanilla::bricks"));
                setBlock(i, j, 11, new Block("vanilla::bricks"));
                setBlock(i, 4, j, new Block("vanilla::bricks"));
                setBlock(i, 11, j, new Block("vanilla::bricks"));
                setBlock(4, i, j, new Block("vanilla::bricks"));
                setBlock(11, i, j, new Block("vanilla::bricks"));
            }
        }
    }
}
