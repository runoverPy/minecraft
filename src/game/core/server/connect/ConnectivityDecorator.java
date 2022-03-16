package game.core.server.connect;

import game.mechanics.blocks.Block;
import game.mechanics.entities.Entity;
import game.core.server.core.Chunk;
import game.core.server.core.Client;
import game.core.server.core.Server;
import org.joml.Vector3i;

import java.io.IOException;

public class ConnectivityDecorator implements Server {
    private final Server server;

    protected ConnectivityDecorator(Server server) {
        this.server = server;
    }

    public static ConnectivityDecorator transferControl(ConnectivityDecorator oldDecorator) {
        return null;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return server.getBlock(x, y, z);
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        server.setBlock(x, y, z, block);
    }

    @Override
    public void updateBlock(int x, int y, int z) {
        server.updateBlock(x, y, z);
    }

    @Override
    public void registerEntity(Entity entity) {
        server.registerEntity(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        server.removeEntity(entity);
    }

    @Override
    public Vector3i getSpawnPoint() {
        return server.getSpawnPoint();
    }

    @Override
    public void update() {
        server.update();
    }

    @Override
    public void registerClient(Client client) {
        server.registerClient(client);
    }

    @Override
    public void disconnectClient(Client client) throws IOException {
        server.disconnectClient(client);
    }

    @Override
    public Chunk getChunk(int cX, int cY, int cZ) {
        return null;
    }

    @Override
    public void close() throws IOException {
        server.close();
    }
}
