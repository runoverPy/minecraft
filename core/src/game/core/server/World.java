package game.core.server;

import game.core.GameRuntime;
import mdk.worldgen.WorldGenerator;
import game.mechanics.entities.Entity;
import game.mechanics.entities.User;
import org.joml.Vector3f;
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
    protected final Map<Vector3i, Integer> refCounter;
    protected final Set<Entity> entities;
    protected final WorldGenerator generator;
    protected final WorldSaver saver;
    private final Set<Client> clients;

    private long serverTime, lastUpdateTime;

    /**
     * Thread termination flag. If set to true, the thread will exit.
     */
    private volatile boolean terminate = false;

    /**
     * to be called by static methods
     */
    public World(WorldGenerator generator) {
        this(generator, null, 0L);
    }

    private World(WorldGenerator generator, WorldSaver saver, long serverTime) {
        this.generator = generator;
        this.saver = saver;
        this.serverTime = serverTime;
        this.lastUpdateTime = System.currentTimeMillis();

        this.entities = new HashSet<>();
        this.clients = new HashSet<>();

        this.chunks = new HashMap<>();
        this.refCounter = new HashMap<>();

        start();
    }

    public static World makeGame(String worldName, WorldGenerator generator) {
        return null;
    }

    public static World loadGame(String worldDirectory) {
        return null;
    }

    public static World demoWorld() {
        return new World(GameRuntime.getInstance().getGeneratorRegister().getGenerator("vanilla:flat_test"));
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
    public void close() {
        this.terminate = true;
        // still needs to save the world
    }

    @Override
    public long getServerTime() {
        return serverTime;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return getChunk(Math.floorDiv(x, 16), Math.floorDiv(y, 16), Math.floorDiv(z, 16))
                .getBlock(Math.floorMod(x, 16), Math.floorMod(y, 16), Math.floorMod(z, 16));
    }

    @Override
    public synchronized void setBlock(int x, int y, int z, Block block) {
        getChunk(Math.floorDiv(x, 16), Math.floorDiv(y, 16), Math.floorDiv(z, 16))
                .setBlock(Math.floorMod(x, 16), Math.floorMod(y, 16), Math.floorMod(z, 16), block);
        updateBlock(x, y, z);
    }

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
    public Vector3f getSpawnPoint() {
        return new Vector3f(0, 0, 1);
    }

    @Override
    public void userUpdate(User.UserStatus status) {

    }

    @Override
    public void registerClient(Client client) {
        clients.add(client);
    }

    @Override
    public void disconnectClient(Client client) {
        clients.remove(client);
    }

    @Override
    public Chunk getChunk(int cX, int cY, int cZ) {
        Chunk c = chunks.get(new Vector3i(cX, cY, cZ));
        if (c == null) {
            c = loadChunk(cX, cY, cZ);
            chunks.put(new Vector3i(cX, cY, cZ), c);
        }
        return c;
    }

    public final void addChunkRef(int cX, int cY, int cZ) {
        Vector3i chunk = new Vector3i(cX, cY, cZ);
        refCounter.putIfAbsent(chunk, 0);
        if (refCounter.get(chunk) <= 0) {
            chunks.put(chunk, loadChunk(cX, cY, cZ));
        }
        refCounter.put(chunk, refCounter.get(chunk) + 1);
    }

    public final void subChunkRef(int cX, int cY, int cZ) {
        Vector3i chunk = new Vector3i(cX, cY, cZ);
        if (refCounter.get(chunk) == null) {
            refCounter.put(chunk, 0);
            return;
        }
        refCounter.put(chunk, refCounter.get(chunk) - 1);
        if (refCounter.get(chunk) <= 0) {
            // unload chunk
            System.out.println(this + String.format(" unloading chunk %d, %d, %d", cX, cY, cZ));
            dropChunk(cX, cY, cZ, chunks.remove(chunk));
        }
    }

    private Chunk loadChunk(int cX, int cY, int cZ) {
        if (saver != null && saver.isChunkSaved(cX, cY, cZ))
            return saver.loadChunk(cX, cY, cZ);
        else {
            Chunk chunk = new Chunk();
            generator.populateChunk(cX, cY, cZ, chunk, Block::new);
            return chunk;
        }
    }

    private void dropChunk(int cX, int cY, int cZ, Chunk chunk) {
        try {
            saver.saveChunk(cX, cY, cZ, chunk);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}