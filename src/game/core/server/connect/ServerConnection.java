package game.core.server.connect;

import game.mechanics.blocks.Block;
import game.mechanics.entities.Entity;
import game.core.server.core.Chunk;
import game.core.server.core.Client;
import game.core.server.core.Server;
import org.joml.Vector3i;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerConnection extends Connection implements Server {
    private final Map<Vector3i, Chunk> chunks;
    private Client client = null;
    private JSONPacket lastPacket = new JSONPacket();

    public ServerConnection(String addr, int port) throws IOException {
        super(new Socket(addr, port));
        chunks = new HashMap<>();
    }

    @Override
    protected void receivePacket(String packet) {

    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return null;
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {

    }

    @Override
    public void updateBlock(Vector3i block) {

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

    /**
     * The method that will cause the exchange of data packets
     */
    @Override
    public void update() {
        // send ping to server

    }

    @Override
    public void registerClient(Client client) {
        if (this.client == null) this.client = client;
        else if (this.client != client) throw new RuntimeException("Cannot assign a second client to a connection");
    }

    @Override
    public void disconnectClient(Client client) throws IOException {
        // essentially closes the connection
        if (this.client == client) {
            close();
            this.client = null;
        }
    }

    @Override
    public Chunk getChunk(int cX, int cY, int cZ) {
        return null;
    }
}
