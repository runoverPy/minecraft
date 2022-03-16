package game.core.server.core;

import game.mechanics.blocks.Block;
import game.mechanics.entities.Entity;
import org.joml.Vector3i;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Code related to maintaining game functions. Includes entity updating, block storage etc.
 * It is intended to run continuously, until stopped, no matter if any users are connected.
 * It maintains its own clock based on System.currentTimeMillis(), with t=0 being the starting time of the world.
 */
public class World extends Thread implements Server {
    protected final Map<Vector3i, Chunk> chunks;
    protected final Set<Entity> entities;
    private final Set<Client> clients;

    private long serverTime, lastUpdateTime;

    /**
     * Thread termination flag. If set to true, the thread will exit.
     */
    private volatile boolean terminate = false;

    /**
     * to be called by static methods
     */
    protected World() {
        this(0L);
    }

    protected World(long storedServerTime) {
        this.serverTime = storedServerTime;
        this.lastUpdateTime = System.currentTimeMillis();
        this.entities = new HashSet<>();
        this.chunks = new HashMap<>();
        this.clients = new HashSet<>();
        start();
    }

    public static void createNew(String name, Long seed, String... settings) {}

    public long getServerTime() {
        return serverTime;
    }

    @Override
    public void run() {
        while (!terminate) {
            long currentTime = System.currentTimeMillis();
            serverTime += currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;
        }
    }

    @Override
    public void close() throws IOException {
        this.terminate = true;
    }

    @Override
    public void updateBlock(int x, int y, int z) {
        clients.forEach(client -> client.updateBlock(x, y, z));
    }

    @Override
    public void registerEntity(Entity entity) {
        entities.add(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        entities.add(entity);
    }

    @Override
    public Vector3i getSpawnPoint() {
        return new Vector3i(0, 0, 1);
    }

    @Override
    public void update() {
        entities.forEach(Entity::update);
    }

    @Override
    public void registerClient(Client client) {
        clients.add(client);
    }

    @Override
    public void disconnectClient(Client client) throws IOException {
        clients.remove(client);
    }

    @Override
    public synchronized Block getBlock(int x, int y, int z) {
        return getChunk(Math.floorDiv(x, 16), Math.floorDiv(y, 16), Math.floorDiv(z, 16))
                .getBlock(Math.floorMod(x, 16), Math.floorMod(y, 16), Math.floorMod(z, 16));
    }

    @Override
    public synchronized void setBlock(int x, int y, int z, Block block) {
        getChunk(Math.floorDiv(x, 16), Math.floorDiv(y, 16), Math.floorDiv(z, 16))
                .setBlock(Math.floorMod(x, 16), Math.floorMod(y, 16), Math.floorMod(z, 16), block);
        updateBlock(x, y, z);
    }

    @Override
    public synchronized Chunk getChunk(int cX, int cY, int cZ) {
        Chunk c = chunks.get(new Vector3i(cX, cY, cZ));
        if (c == null) {
            c = new Chunk();
            System.out.println(this + String.format("Creating new chunk for %d, %d, %d", cX, cY, cZ));
            chunks.put(new Vector3i(cX, cY, cZ), c);
        }
        return c;
    }
}