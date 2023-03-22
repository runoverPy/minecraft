package mdk.blocks;

public interface Chunk <B extends Block> {
    B getBlock(int x, int y, int z);

    void setBlock(int x, int y, int z, B block);
}
