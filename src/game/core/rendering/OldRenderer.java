package game.core.rendering;


import game.core.server.Chunk;
import game.core.server.Server;
import game.mechanics.blocks.Block;
import game.mechanics.blocks.Material;
import game.mechanics.blocks.Phase;
import game.util.Side;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.joml.Matrix4f;
import org.joml.Vector3i;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

import static org.lwjgl.opengl.GL46.*;

public class OldRenderer implements Renderer {
    private final Server server;

    private final ExecutorService executor;
    private final ExecutorService reloader;
    private final ExecutorService updater;

    private final Map<Vector3i, OldRenderer.RenderingChunk> chunks;

    public OldRenderer(Server server) {
        this.server = server;

        this.executor = new ThreadPoolExecutor(0, 4096, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        this.reloader = new ThreadPoolExecutor(16, 256, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        this.updater = Executors.newSingleThreadExecutor();
        this.chunks = new HashMap<>();
    }

    @Override
    public void draw(Matrix4f matrixPV) {
        chunks.values().forEach(chunk -> chunk.draw(matrixPV));
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

        Set<Vector3i> loadedChunks = getLoadedChunks();

        // all in newChunks and not in loadedChunks
        Set<Vector3i> toLoad = new HashSet<>(newChunks);
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

            for (Side side : Side.values()) {
                Vector3i nextBlock = block.add(side.getVec(), new Vector3i());

                Block block2 = server.getBlock(nextBlock);
                Phase blockPhase2 = block2.getPhase();

                synchronized (this) {
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
        });
    }

    @Override
    public void loadChunk(Vector3i chunkPos) {
        if (!containsChunk(chunkPos)) {
            RenderingChunk newChunk = new RenderingChunk(chunkPos);
            chunks.put(chunkPos, newChunk);
            executor.execute(newChunk::map);
        }
    }

    @Override
    public void dropChunk(Vector3i chunk) {
        if (containsChunk(chunk)) {
            chunks.remove(chunk);
        }
    }

    public void loadChunks(Set<Vector3i> chunks) {
        chunks.forEach(this::loadChunk);
    }

    public void dropChunks(Set<Vector3i> chunks) {
        chunks.forEach(this::dropChunk);
    }

    public void addFace(Face face, Material type) {
        getChunkForBlock(face.getPosition()).addFace(face, type, true);
    }

    public void delFace(Face face) {
        getChunkForBlock(face.getPosition()).delFace(face, true);
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

    private class RenderingChunk {
        private final Vector3i chunkPos;
        private final Map<Face, Material> rawFaces;
        private final Map<Material, Triplet<Integer, Integer, Integer>> renderData;

        private final UploadOperation upload;

        private RenderingChunk(Vector3i chunkPos) {
            this.chunkPos = chunkPos;
            this.rawFaces = new HashMap<>();
            this.renderData = new HashMap<>();
            this.upload = new UploadOperation();
        }

        public void draw(Matrix4f matrixPV) {
            this.upload.runIfPossible();

            glBindVertexBuffer(0, RenderUtils.vertexBuffer, 0, 20);
            glEnableVertexAttribArray(0);
            glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
            glVertexAttribBinding(0, 0);
            glEnableVertexAttribArray(1);
            glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
            glVertexAttribBinding(1, 0);
            glUseProgram(RenderUtils.programID);
            glUniformMatrix4fv(RenderUtils.fullTransform, false, matrixPV.get(new float[16]));

            synchronized (renderData) {
                renderData.forEach((material, objects) -> {
                    int drawCount = objects.getValue0();
                    int indirectBuffer = objects.getValue1();
                    int positionBuffer = objects.getValue2();

                    glBindBuffer(GL_DRAW_INDIRECT_BUFFER, indirectBuffer);
                    glBindVertexBuffer(1, positionBuffer, 0, 12);
                    glEnableVertexAttribArray(2);
                    glVertexAttribFormat(2, 3, GL_FLOAT, false, 0);
                    glVertexAttribBinding(2, 1);
                    glVertexAttribDivisor(2, 1);

                    material.bindTexture();
                    glMultiDrawArraysIndirect(GL_TRIANGLE_FAN, 0L, drawCount, 20);
                    material.loseTexture();
                });
            }
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
            Map<Material, Map<Side, List<Vector3i>>> sorted2 = new HashMap<>();
            synchronized (rawFaces) {
                for (Entry<Face, Material> entry : rawFaces.entrySet()) {
                    Material material = entry.getValue();
                    Side side = entry.getKey().getSide();
                    Vector3i pos = entry.getKey().getPosition();

                    if (!sorted2.containsKey(material))
                        sorted2.put(material, new HashMap<>());
                    if (!sorted2.get(material).containsKey(side))
                        sorted2.get(material).put(side, new LinkedList<>());
                    sorted2.get(material).get(side).add(pos);
                }
            }

            Map<Material, Triplet<Integer, IntBuffer, FloatBuffer>> bufferData = new HashMap<>();
            sortedFaces.forEach((material, faces) -> {
                int drawCount = faces.size();
                IntBuffer indirect = MemoryUtil.memAllocInt(drawCount * 4);
                FloatBuffer position = MemoryUtil.memAllocFloat(drawCount * 4);

                int i = 0;
                for (Face face : faces) {
                    indirect.put(4)
                            .put(1)
                            .put(4 * face.getSide().ordinal())
                            .put(i);
                    position.put(face.getPosition().x())
                            .put(face.getPosition().y())
                            .put(face.getPosition().z());
                    i++;
                }
                indirect.flip();
                position.flip();
                bufferData.put(material, new Triplet<>(i, indirect, position));
            });
            upload.setOperation(() -> upload(bufferData));
        }

        public void upload(Map<Material, Triplet<Integer, IntBuffer, FloatBuffer>> bufferData) {
            for (Triplet<Integer, Integer, Integer> buffers : renderData.values()) {
                glDeleteBuffers(buffers.getValue1());
                glDeleteBuffers(buffers.getValue2());
            }
            renderData.clear();
            for (Map.Entry<Material, Triplet<Integer, IntBuffer, FloatBuffer>> buffers : bufferData.entrySet()) {
                int indirectBuffer = glGenBuffers();
                int positionBuffer = glGenBuffers();
                glBindBuffer(GL_DRAW_INDIRECT_BUFFER, indirectBuffer);
                glBufferData(GL_DRAW_INDIRECT_BUFFER, buffers.getValue().getValue1(), GL_STATIC_READ);
                glBindBuffer(GL_ARRAY_BUFFER, positionBuffer);
                glBufferData(GL_ARRAY_BUFFER, buffers.getValue().getValue2(), GL_STATIC_DRAW);
                MemoryUtil.memFree(buffers.getValue().getValue1());
                MemoryUtil.memFree(buffers.getValue().getValue2());
                float[] positionData = new float[buffers.getValue().getValue0() * 4];
                glGetBufferSubData(GL_ARRAY_BUFFER, 0, positionData);
                System.out.println("position data: " + Arrays.toString(positionData));
                renderData.put(buffers.getKey(), new Triplet<>(buffers.getValue().getValue0(), indirectBuffer, positionBuffer));
            }
        }

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
                            addFace(new Face(prevBlock, side), block1.getMaterial(), false);
                        } else {
                            if (containsBlock(nextBlock)) {
                                addFace(new Face(nextBlock, side.invert()), block2.getMaterial(), false);
                            }
                        }
                    }
                    if (containsBlock(nextBlock)) processingQueue.add(new Pair<>(nextBlock, block2));
                }
            }
            submitReload();
        }

        private void submitReload() {
            reloader.execute(this::update);
        }

        public void delFace(Face face, boolean reload) {
            synchronized (this.rawFaces) {
                rawFaces.remove(face);
            }
            if (reload) submitReload();
        }

        public void addFace(Face face, Material material, boolean reload) {
            synchronized (this.rawFaces) {
                rawFaces.put(face, material);
            }
            if (reload) submitReload();
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
}
