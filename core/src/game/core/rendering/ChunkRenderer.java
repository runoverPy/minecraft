package game.core.rendering;

import game.core.server.Server;
import org.joml.Matrix4f;
import org.joml.Vector3i;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ChunkRenderer<C extends RenderingChunk> implements Renderer, Closeable {
    protected final Server server;
    private final BlockingQueue<Runnable> executorQueue;
    protected final ThreadPoolExecutor executor;
    private final AtomicInteger executorTasks = new AtomicInteger();
    protected final Map<Vector3i, C> chunks;

    public ChunkRenderer(Server server) {
        this.server = server;
        this.chunks = new HashMap<>();

        ThreadFactory threadFactory = r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        };

        this.executorQueue = new LinkedBlockingQueue<>();
        this.executor = new ThreadPoolExecutor(4, 16, 60, TimeUnit.SECONDS, executorQueue, threadFactory) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                executorTasks.incrementAndGet();
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                executorTasks.decrementAndGet();
                super.afterExecute(r, t);
            }
        };
    }

    public int getExecutorQueueSize() {
        return executorQueue.size();
    }

    public int getExecutorTasks() {
        return executorTasks.get();
    }

    public int getExecutorThreads() {
        return executor.getPoolSize();
    }

    public ExecutorService getExecutor() {
        return executor;
    }


    @Override
    public void draw(Matrix4f matrixPV) {
        chunks.values().forEach(chunk -> chunk.draw(matrixPV));
    }

    @Override
    public void updateChunks(Vector3i centerChunk, int renderRadius, boolean circular) {
        Set<Vector3i> newChunks = new HashSet<>();
        for (int x = centerChunk.x() - renderRadius; x <= centerChunk.x() + renderRadius; x++) {
            for (int y = centerChunk.y() - renderRadius; y <= centerChunk.y() + renderRadius; y++) {
                for (int z = centerChunk.z() - renderRadius; z <= centerChunk.z() + renderRadius; z++) {
                    Vector3i chunk = new Vector3i(x, y, z);
                    if (centerChunk.distance(chunk) < renderRadius || !circular) newChunks.add(chunk);
                }
            }
        }

        Set<Vector3i> loadedChunks = getChunks();

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

        loadChunks(toLoad);
        dropChunks(toUnload);
    }

    @Override
    public int getChunkCount() {
        return chunks.size();
    }

    public boolean containsChunk(Vector3i chunk) {
        return chunks.containsKey(chunk);
    }

    public boolean containsBlock(Vector3i block) {
        return chunks.containsKey(new Vector3i(
                Math.floorDiv(block.x(), 16),
                Math.floorDiv(block.y(), 16),
                Math.floorDiv(block.z(), 16)
        ));
    }

    private Set<Vector3i> getChunks() {
        return chunks.keySet();
    }

    public C getChunkForBlock(Vector3i block) {
        Vector3i chunk = new Vector3i(
                Math.floorDiv(block.x(), 16),
                Math.floorDiv(block.y(), 16),
                Math.floorDiv(block.z(), 16)
        );
        C out;
        synchronized (chunks) {
            out = chunks.get(chunk);
        }
        if (out == null) {
            throw new NullPointerException("Error getting chunk " + chunk);
        }
        return out;
    }

    @Override
    public void close() throws IOException {
        this.executor.shutdown();
    }
}
