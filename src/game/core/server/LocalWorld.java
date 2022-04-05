package game.core.server;

import game.mechanics.blocks.Block;
import game.mechanics.entities.Entity;
import org.joml.Vector3i;

import java.io.IOException;

public class LocalWorld extends World implements Server {
    public LocalWorld() {
        super();
    }

    public LocalWorld(String worldName) {
        super();
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return null;
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {

    }

    @Override
    public void updateBlock(int x, int y, int z) {

    }

    @Override
    public void registerEntity(Entity entity) {

    }

    @Override
    public void removeEntity(Entity entity) {

    }

    @Override
    public Vector3i getSpawnPoint() {
        return null;
    }

    @Override
    public void registerClient(Client client) {

    }

    @Override
    public void disconnectClient(Client client) throws IOException {

    }

    @Override
    protected Chunk loadChunk(int cX, int cY, int cZ) {
        return null;
    }

    @Override
    protected void unloadChunk(int cX, int cY, int cZ, Chunk chunk) {

    }

}
