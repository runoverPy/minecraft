package game.core.rendering;

import game.core.server.Chunk;
import game.core.server.Server;
import game.mechanics.blocks.Block;
import game.mechanics.blocks.Material;
import game.mechanics.blocks.Phase;
import game.util.Side;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector3i;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public abstract class ChunkRenderer implements Renderer, Closeable {
    protected final Server server;
    protected final ExecutorService executor;
    protected final ExecutorService reloader;
    protected final ExecutorService updater;
    protected final Map<Vector3i, RenderingChunk> chunks;

    public ChunkRenderer(Server server) {
        this.server = server;
        this.chunks = new HashMap<>();

        ThreadFactory threadFactory = r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        };

        this.executor = new ThreadPoolExecutor(0, 4096, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), threadFactory);
        this.reloader = new ThreadPoolExecutor(16, 256, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), threadFactory);
        this.updater = Executors.newSingleThreadExecutor(threadFactory);
    }

    @Override
    public void draw(Matrix4f matrixPV) {
        chunks.values().forEach(chunk -> chunk.draw(matrixPV));
    }

    @Override
    public void updateChunks(Vector3i centerChunk, int renderRadius, boolean circular) {
        System.out.println("updating chunks");
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

        Set<Vector3i> loadedChunks = getLoadedChunks();

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
    public void updateBlock(Vector3i block) {
        updater.execute(() -> {
            if (!containsBlock(block)) return;

            Block block1 = server.getBlock(block);
            Phase blockPhase1 = block1.getPhase();

            Set<RenderingChunk> affectedChunks = new HashSet<>();
            affectedChunks.add(getChunkForBlock(block));

            for (Side side : Side.values()) {
                Vector3i nextBlock = block.add(side.getVec(), new Vector3i());

                Block block2 = server.getBlock(nextBlock);
                Phase blockPhase2 = block2.getPhase();

                synchronized (this) {
                    affectedChunks.add(getChunkForBlock(nextBlock));
                    if (blockPhase1 != blockPhase2) {
                        // add a new face
                        if (blockPhase1 != Phase.GAS) {
                            addFace(new Face(block, side), block1.getMaterial());
                        } else {
                            addFace(new Face(nextBlock, side.invert()), block2.getMaterial());
                        }
                    } else {
                        // remove a face
                        delFace(new Face(block, side));
                        delFace(new Face(nextBlock, side.invert()));
                    }
                }
            }
            affectedChunks.forEach(RenderingChunk::submitReload);
        });
    }

    public void loadChunks(Set<Vector3i> chunks) {
        chunks.forEach(this::loadChunk);
    }

    public void dropChunks(Set<Vector3i> chunks) {
        chunks.forEach(this::dropChunk);
    }

    public void addFace(Face face, Material type) {
        getChunkForBlock(face.getPosition()).addFace(face, type);
    }

    public void delFace(Face face) {
        getChunkForBlock(face.getPosition()).delFace(face);
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

    private Set<Vector3i> getLoadedChunks() {
        return chunks.keySet();
    }

    private RenderingChunk getChunkForBlock(Vector3i block) {
        Vector3i chunk = new Vector3i(
                Math.floorDiv(block.x(), 16),
                Math.floorDiv(block.y(), 16),
                Math.floorDiv(block.z(), 16)
        );
        RenderingChunk out;
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
        this.reloader.shutdown();
        this.updater.shutdown();
    }

    public abstract class RenderingChunk {
        protected final Map<Face, Material> faces;
        private final Vector3i chunkPos;

        public RenderingChunk(Vector3i chunkPos) {
            this.chunkPos = chunkPos;
            this.faces = new HashMap<>();
        }

        public abstract void draw(Matrix4f matrixPV);

        public void map() {
            final Queue<Pair<Vector3i, Block>> processingQueue = new ArrayDeque<>();
            final Set<Vector3i> visitedBlocks = new HashSet<>();

            Chunk chunk = server.getChunk(getChunkPos());
            processingQueue.add(new Pair<>(getChunkPos().mul(16), chunk.getBlock(0, 0, 0)));

            while (!processingQueue.isEmpty()) {
                Pair<Vector3i, Block> active = processingQueue.remove();
                Vector3i prevBlock = active.getValue0();

                if (visitedBlocks.contains(prevBlock)) continue;
                else visitedBlocks.add(prevBlock);

                Block block1 = active.getValue1();
                Phase blockPhase1 = block1.getPhase();

                for (Side side : Side.values()) {
                    Vector3i nextBlock = prevBlock.add(side.getVec(), new Vector3i());

                    // ist nächster Block erlaubt?
                    Block block2 = server.getBlock(nextBlock);
                    Phase blockPhase2 = block2.getPhase();

                    if (blockPhase1 != blockPhase2) { // bei jedem Phasenübergang
                        if (blockPhase1 != Phase.GAS) {
                            addFace(new Face(prevBlock, side), block1.getMaterial());
                        } else {
                            if (containsBlock(nextBlock)) {
                                addFace(new Face(nextBlock, side.invert()), block2.getMaterial());
                            }
                        }
                    }
                    if (containsBlock(nextBlock)) processingQueue.add(new Pair<>(nextBlock, block2));
                }
            }
            submitReload();
        }
        public abstract void update();

        public final void delFace(Face face) {
            synchronized (this.faces) {
                faces.remove(face);
            }
        }

        public final void addFace(Face face, Material material) {
            synchronized (this.faces) {
                faces.put(face, material);
            }
        }

        public Vector3i getChunkPos() {
            return new Vector3i(chunkPos);
        }

        public boolean containsBlock(Vector3i block) {
            return getChunkPos().equals(new Vector3i(
                    Math.floorDiv(block.x(), 16),
                    Math.floorDiv(block.y(), 16),
                    Math.floorDiv(block.z(), 16)
            ));
        }

        public void submitReload() {
            reloader.execute(this::update);
        }
    }
}
