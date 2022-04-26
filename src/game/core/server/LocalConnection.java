package game.core.server;

import game.mechanics.blocks.Block;
import game.mechanics.entities.Entity;
import game.mechanics.entities.User;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.io.IOException;

public class LocalConnection implements Server, Client {
    private final World world;
    private Client client;

    public LocalConnection(World world) {
        this.world = world;
    }

    @Override
    public void updateBlock(Vector3i block) {
        client.updateBlock(block);
    }

    @Override
    public void updateBlock(int x, int y, int z) {
        client.updateBlock(x, y, z);
    }

    @Override
    public void userUpdate(Entity.EntityUpdate update) {
        client.userUpdate(update);
    }

    @Override
    public void loadChunk(int cX, int cY, int cZ) {
        world.addChunkRef(cX, cY, cZ);
        client.loadChunk(cX, cY, cZ);
    }

    @Override
    public void dropChunk(int cX, int cY, int cZ) {
        world.addChunkRef(cX, cY, cZ);
        client.dropChunk(cX, cY, cZ);
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return world.getBlock(x, y, z);
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        world.setBlock(x, y, z, block);
    }

    @Override
    public Chunk getChunk(int cX, int cY, int cZ) {
        return world.getChunk(cX, cY, cZ);
    }

    @Override
    public long getServerTime() {
        return world.getServerTime();
    }

    @Override
    public Vector3f getSpawnPoint() {
        return world.getSpawnPoint();
    }

    @Override
    public void userUpdate(User.UserStatus status) {
        world.userUpdate(status);
    }

    @Override
    public void registerEntity(Entity entity) {
        world.registerEntity(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        world.registerEntity(entity);
    }

    @Override
    public void registerClient(Client client) {
        world.registerClient(client);
    }

    @Override
    public void disconnectClient(Client client) throws IOException {
        world.disconnectClient(client);
    }

    @Override
    public void close() throws IOException {
        world.close();
    }
}
