package game.core.server;

import game.core.GameRuntime;
import mdk.blocks.Phase;
import mdk.worldgen.WorldGenerator;
import game.mechanics.entities.Entity;
import game.mechanics.entities.User;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.io.IOException;
import java.util.*;

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
        synchronized (chunks) {
            chunks.clear();
        }
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
        int centerX = 0, centerY = 0, radius = 8;
        Random rng = new Random();
        int cycles = 0;
        outer: do {
            if (cycles++ >= 256) throw new RuntimeException("Infinite Loop");
            int
              spawnX = rng.nextInt(2 * radius) - radius + centerX,
              spawnY = rng.nextInt(2 * radius) - radius + centerY;

            int i = 256;
            while (getBlock(spawnX, spawnY, i).getPhase() == Phase.GAS) {
                if (--i < 0) continue outer;
            }
            return new Vector3f(spawnX + 0.5f, spawnY + 0.5f, i + 1);
        } while (true);
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
        synchronized (chunks) {
            Chunk c = chunks.get(new Vector3i(cX, cY, cZ));
            if (c == null) {
                c = loadChunk(cX, cY, cZ);
                chunks.put(new Vector3i(cX, cY, cZ), c);
            }
            return c;
        }
    }

    @Override
    public int getChunkCount() {
        return chunks.size();
    }

    @Override
    public void updateChunks(Vector3i centerChunk, int renderRadius, boolean circular) {
        Set<Vector3i> newChunks = new HashSet<>();
        Vector3i chunk;
        for (int x = centerChunk.x() - renderRadius; x <= centerChunk.x() + renderRadius; x++) {
            for (int y = centerChunk.y() - renderRadius; y <= centerChunk.y() + renderRadius; y++) {
                for (int z = centerChunk.z() - renderRadius; z <= centerChunk.z() + renderRadius; z++) {
                    chunk = new Vector3i(x, y, z);
                    if (centerChunk.distance(chunk) < renderRadius || !circular) newChunks.add(chunk);
                }
            }
        }

        Set<Vector3i> loadedChunks;
        synchronized (chunks) {
            loadedChunks = chunks.keySet();
            // all in newChunks and not in loadedChunks
            SortedSet<Vector3i> toLoad = new TreeSet<>((o1, o2) -> {
                int LT = -1, EQ = 0, GT = 1;
                double dist = new Vector3i(o1).sub(centerChunk).length() - new Vector3i(o2).sub(centerChunk).length();
                if (o1 == o2) return EQ;
                if (dist == 0d) return GT;
                return dist < 0d ? LT : GT;
            });
            toLoad.addAll(newChunks);
            toLoad.removeAll(loadedChunks);

            // all in loadedChunks and not in newChunks
            Set<Vector3i> toUnload = new HashSet<>(loadedChunks);
            toUnload.removeAll(newChunks);

//            for (Vector3i chunkToLoad : toLoad) {
//                addChunkRef(chunkToLoad.x, chunkToLoad.y, chunkToLoad.z);
//            }
            for (Vector3i chunkToUnload : toUnload) {
                dropChunk(chunkToUnload.x, chunkToUnload.y, chunkToUnload.z, chunks.remove(chunkToUnload));
            }
        }
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
            dropChunk(cX, cY, cZ, chunks.remove(chunk));
        }
    }

    private Chunk loadChunk(int cX, int cY, int cZ) {
        if (saver != null && saver.isChunkSaved(cX, cY, cZ))
            return saver.loadChunk(cX, cY, cZ);
        else {
            Chunk chunk = new Chunk(cX, cY, cZ);
            generator.populateChunk(cX, cY, cZ, chunk, Block::new);
            return chunk;
        }
    }

    private void dropChunk(int cX, int cY, int cZ, Chunk chunk) {
        try {
            if (saver != null) saver.saveChunk(cX, cY, cZ, chunk);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}