package game.mechanics.rendering;

import game.assets.Face;
import game.mechanics.blocks.Block;
import game.mechanics.blocks.Material;
import game.mechanics.blocks.Phase;
import game.core.server.core.Chunk;
import game.core.server.core.Server;
import game.util.Side;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.lwjgl.opengl.GL46.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL46.glDrawArrays;

public class UnifiedRenderer implements Renderer {
    private final Server server;
    private final ChunkManager chunkManager;

    private final Worker processor;

    public UnifiedRenderer(Server server) {
        this.server = server;
        this.chunkManager = new ChunkManager();
        this.processor = new Worker();

        Thread processorThread = new Thread(this.processor, "processing chunk 1");
        processorThread.setDaemon(true);
        processorThread.start();
    }

    @Override
    public void updateBlock(Vector3i block) {
        processor.loadBlock(block);
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

        Set<Vector3i> loadedChunks = chunkManager.getLoadedChunks();

        // all in newChunks and not in loadedChunks
        Set<Vector3i> toLoad = new HashSet<>(newChunks);
        toLoad.removeAll(loadedChunks);

        // all in loadedChunks and not in newChunks
        Set<Vector3i> toUnload = new HashSet<>(loadedChunks);
        toUnload.removeAll(newChunks);

        chunkManager.loadChunks(toLoad);
        chunkManager.unloadChunks(toUnload);
    }

    @Override
    public void draw(Matrix4f matrixPV) {
        chunkManager.draw(matrixPV);
    }

    /**
     * the runnable object which allows for the main thread do rendering
     * without being interrupted while updating the rendering maps
     */
    private class Worker implements Runnable {
        private final Queue<Vector3i> loadBlocks;
        private final Queue<RenderingChunk> loadChunks;
        private final Queue<RenderingChunk> chunkUpdates;
        private Executor executor = Executors.newSingleThreadExecutor();

        public Worker() {
            this.loadBlocks = new LinkedList<>();
            this.loadChunks = new LinkedList<>();
            this.chunkUpdates = new LinkedList<>();
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (loadBlocks) {
                    while (!loadBlocks.isEmpty()) {
                        remapBlock(loadBlocks.remove());
                    }
                }
                synchronized (loadChunks) {
                    while (!loadChunks.isEmpty()) {
                        loadChunks.remove().map();
                    }
                }
                synchronized (chunkUpdates) {
                    while (!chunkUpdates.isEmpty()) {
                        chunkUpdates.remove().update();
                    }
                }
            }
        }

        public synchronized void loadBlock(Vector3i block) {
            this.loadBlocks.add(block);
            notify();
        }

        public synchronized void loadChunk(RenderingChunk chunk) {
            this.loadChunks.add(chunk);
            notify();
        }

        public synchronized void loadChunks(Collection<RenderingChunk> chunks) {
            this.loadChunks.addAll(chunks);
            notify();
        }

        public synchronized void reloadChunk(RenderingChunk chunk) {
            chunkUpdates.add(chunk);
            notify();
        }

        /**
         * remaps a single block
         * @param block the coordinate of the block which is to be remapped
         */
        private void remapBlock(Vector3i block) {
            if (!chunkManager.containsBlock(block)) return;
            synchronized (server) {
                Block block1 = server.getBlock(block);
                Phase blockPhase1 = block1.getPhase();

                for (Side side : Side.values()) {
                    Vector3i nextBlock = block.add(side.getVec(), new Vector3i());

                    Block block2 = server.getBlock(nextBlock);
                    Phase blockPhase2 = block2.getPhase();

                    synchronized (chunkManager) {
                        if (blockPhase1 != blockPhase2) {
                            // add a new face
                            if (blockPhase1 != Phase.GAS) {
                                chunkManager.addFace(new Face(block, side), block1.getMaterial());
                            } else {
                                chunkManager.addFace(new Face(nextBlock, side.invert()), block2.getMaterial());
                            }
                        } else {
                            // remove a face
                            chunkManager.delFace(new Face(block, side));
                            chunkManager.delFace(new Face(nextBlock, side.invert()));
                        }
                    }
                }
            }
        }
    }

    private class RenderingChunk {
        private final Vector3i chunkPos;
        private final Set<Vector3i> visited;
        private final Map<Face, Material> rawFaces;
        private final Map<Material, Face[]> sortedFaces;
        private final Map<Material, Triplet<int[], int[], FloatBuffer>> newData;
        private final Map<Material, Triplet<int[], int[], Integer>> renderData;
        private volatile boolean canRefresh = false;

        // private final Map<Face, Class<? extends Block>> rf2
        // private final Map<Class<? extends Block>, Triplet<int[], int[], Integer>> rd2;


        private RenderingChunk(Vector3i chunkPos) {
            this.chunkPos = chunkPos;
            this.rawFaces = new HashMap<>();
            this.sortedFaces = new HashMap<>();
            this.newData = new HashMap<>();
            this.renderData = new HashMap<>();
            this.visited = new HashSet<>();
        }

        public void draw(Matrix4f matrixPV) {
            RenderUtils.open(matrixPV, () -> {
                synchronized (sortedFaces) {
                    sortedFaces.forEach((type, faces) -> {
                        type.withTexture(() -> {
                            for (Face face : faces) {
                                RenderUtils.setBlockOffset(new Vector3f(face.getPos()));
                                glDrawArrays(GL_TRIANGLE_FAN, face.getSide().ordinal() * 4, 4);
                            }
                        });
                    });
                }
            });
        }

        public void map() {
            Queue<Pair<Vector3i, Block>> processingQueue = new ArrayDeque<>();


            Chunk chunk = server.getChunk(getChunkPos());
            processingQueue.add(new Pair<>(getChunkPos().mul(16), chunk.getBlock(0, 0, 0)));

            while (!processingQueue.isEmpty()) {
                Pair<Vector3i, Block> active = processingQueue.remove();
                Vector3i prevBlock = active.getValue0();

                if (isVisited(prevBlock)) continue;
                markVisited(prevBlock);

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

                    // Blocks outside bounds should not be processed further
                    if (containsBlock(nextBlock)) processingQueue.add(new Pair<>(nextBlock, block2));
                }
            }
            submitReload();
        }

        public void update() {
            Map<Material, List<Face>> sortedFaces = new HashMap<>();
            synchronized (rawFaces) {
                rawFaces.forEach((face, type) -> {
                    synchronized (sortedFaces) {
                        if (!sortedFaces.containsKey(type)) sortedFaces.put(type, new LinkedList<>());
                        sortedFaces.get(type).add(face);
                    }
                });
            }
            synchronized (this.sortedFaces) {
                this.sortedFaces.clear();
                sortedFaces.forEach(((type, faces) -> {
                    this.sortedFaces.put(type, faces.toArray(new Face[0]));
                }));
            }

            synchronized (newData) {
                newData.clear();
                sortedFaces.forEach((type, list) -> {
                    int size = list.size();

                    int[] firsts = new int[size];
                    int[] counts = new int[size];
                    FloatBuffer transfer = MemoryUtil.memAllocFloat(4 * list.size());

                    for (int i = 0; i < list.size(); i++) {
                        Face face = list.get(i);
                        firsts[i] = 4 * face.getSide().ordinal();
                        counts[i] = 4;
                        new Vector3f(face.getPos()).get(4 * i, transfer);
                    }
                    newData.put(type, new Triplet<>(firsts, counts, transfer));
                });
            }
            canRefresh = true;
        }

        private void submitReload() {
            processor.reloadChunk(this);
        }

        public void delFace(Face face) {
            rawFaces.remove(face);
            submitReload();
        }

        public void addFace(Face face, Material material) {
            rawFaces.put(face, material);
            submitReload();
        }

        public void markVisited(Vector3i block) {
            this.visited.add(block);
        }

        public void unMarkVisited(Vector3i block) {
            this.visited.remove(block);
        }

        public boolean isVisited(Vector3i block) {
            return this.visited.contains(block);
        }

        public Vector3i getChunkPos() {
            return new Vector3i(chunkPos);
        }

        public boolean containsBlock(Vector3i block) {
            return chunkPos.equals(new Vector3i(
                    Math.floorDiv(block.x(), 16),
                    Math.floorDiv(block.y(), 16),
                    Math.floorDiv(block.z(), 16)
            ));
        }
    }

    private class ChunkManager {
        private final Map<Vector3i, RenderingChunk> chunks;

        public ChunkManager() {
            this.chunks = new HashMap<>();
        }

        public Set<Vector3i> getLoadedChunks() {
            return this.chunks.keySet();
        }

        public void loadChunk(Vector3i chunkPos) {
            RenderingChunk newChunk = new RenderingChunk(chunkPos);
            this.chunks.put(chunkPos, newChunk);
            processor.loadChunk(newChunk);
        }

        public void loadChunks(Set<Vector3i> chunks) {
            Queue<RenderingChunk> newChunks = new LinkedList<>();
            for (Vector3i chunk : chunks) {
                RenderingChunk newChunk = new RenderingChunk(chunk);
                synchronized (this.chunks) {
                    this.chunks.put(chunk, newChunk);
                }
                newChunks.add(newChunk);
            }
            processor.loadChunks(newChunks);
        }

        public void unloadChunk(Vector3i chunkPos) {
            synchronized (chunks) {
                this.chunks.remove(chunkPos);
            }
        }

        public void unloadChunks(Set<Vector3i> chunks) {
            chunks.forEach(this::unloadChunk);
        }


        public void addFace(Face face, Material type) {
            getChunkForBlock(face.getPos()).addFace(face, type);
        }

        public void delFace(Face face) {
            getChunkForBlock(face.getPos()).delFace(face);
        }


        public void draw(Matrix4f matrixPV) {
            chunks.values().forEach(chunk -> chunk.draw(matrixPV));
        }

        public boolean containsBlock(Vector3i block) {
            return chunks.containsKey(new Vector3i(
                    Math.floorDiv(block.x(), 16),
                    Math.floorDiv(block.y(), 16),
                    Math.floorDiv(block.z(), 16)
            ));
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
    }
}
