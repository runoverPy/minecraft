package game.core.rendering;

import game.core.GameRuntime;
import game.core.server.Block;
import game.core.server.Chunk;
import game.core.server.Server;
import game.util.Side;
import game.util.Util;
import mdk.blocks.Phase;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.joml.Matrix4f;
import org.joml.Vector3i;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.*;
import java.util.Map.Entry;

import mdk.blocks.Material;

import static org.lwjgl.opengl.GL46.*;

public class IndirectRenderer extends ChunkRenderer<IndirectRenderer.IndirectRenderingChunk> {
    private static final int programID;
    private static final int fullTransform;

    static {
        programID = Util.genProgram(
                new Pair<>("/shaders/surface-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/block-fs.glsl", GL_FRAGMENT_SHADER)
        );
        fullTransform = glGetUniformLocation(programID, "fullTransform");
    }

    public IndirectRenderer(Server server) {
        super(server);
    }

    @Override
    public void loadChunk(Vector3i chunk) {
        if (!containsChunk(chunk)) {
            IndirectRenderingChunk newChunk = new IndirectRenderingChunk(chunk);
            chunks.put(chunk, newChunk);
            executor.execute(newChunk::map);
        }
    }

    @Override
    public void dropChunk(Vector3i chunk) {
        if (containsChunk(chunk)) {
            chunks.remove(chunk);
        }
    }

    @Override
    public MaterialDatabase getMaterialDatabase() {
        return null;
    }

    @Override
    public void updateBlock(Vector3i block) {
        getExecutor().execute(() -> {
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
            for (RenderingChunk chunk : affectedChunks) {
                chunk.requestUpload();
            }
        });
    }

    // todo move to IndirectRenderer or other class
    public void addFace(Face face, Material type) {
        getChunkForBlock(face.getPosition()).addFace(face, type);
    }

    public void delFace(Face face) {
        getChunkForBlock(face.getPosition()).delFace(face);
    }

    protected class IndirectRenderingChunk extends RenderingChunk {
        private final Map<Material, Triplet<Integer, Integer, Integer>> renderData;

        private boolean doUpload = false;

        private IndirectRenderingChunk(Vector3i chunkPos) {
            super(chunkPos);
            this.renderData = new HashMap<>();
        }

        @Override
        public void draw(Matrix4f matrixPV) {
            if (doUpload) {
                upload();
                doUpload = false;
            }

            glBindVertexBuffer(0, RenderUtils.vertexBuffer, 0, 20);
            glEnableVertexAttribArray(0);
            glVertexAttribFormat(0, 3, GL_FLOAT, false, 0);
            glVertexAttribBinding(0, 0);
            glEnableVertexAttribArray(1);
            glVertexAttribFormat(1, 2, GL_FLOAT, false, 3 * 4);
            glVertexAttribBinding(1, 0);
            glUseProgram(programID);
            glUniformMatrix4fv(fullTransform, false, matrixPV.get(new float[16]));

            synchronized (renderData) {
                renderData.forEach((material, objects) -> {
                    int drawCount = objects.getValue0();
                    int indirectBuffer = objects.getValue1();
                    int positionBuffer = objects.getValue2();

                    glBindBuffer(GL_DRAW_INDIRECT_BUFFER, indirectBuffer);
                    glBindBuffer(GL_ARRAY_BUFFER, positionBuffer);

                    glEnableVertexAttribArray(2);
                    glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0L);
                    glVertexAttribDivisor(2, 1);

                    MaterialDatabase materialDatabase = GameRuntime.getInstance().getMaterialDatabase();
                    materialDatabase.bindMaterial(material);
                    glMultiDrawArraysIndirect(GL_TRIANGLE_FAN, 0L, drawCount, 16);
                    materialDatabase.loseMaterial();
                });
            }
        }

        @Override
        public void requestUpload() {
            doUpload = true;
        }

        public void upload() {
            Map<Material, Map<Side, List<Vector3i>>> sortedFaces = new HashMap<>();
            synchronized (faces) {
                faces.forEach((face, material) -> {
                    synchronized (sortedFaces) {
                        if (!sortedFaces.containsKey(material))
                            sortedFaces.put(material, new HashMap<>());
                        if (!sortedFaces.get(material).containsKey(face.getSide()))
                            sortedFaces.get(material).put(face.getSide(), new LinkedList<>());
                        sortedFaces.get(material).get(face.getSide()).add(face.getPosition());
                    }
                });
            }

            Map<Material, Triplet<Integer, IntBuffer, FloatBuffer>> bufferData = new HashMap<>();
            sortedFaces.forEach((material, sides) -> {
                int drawCount = faces.size();
                IntBuffer indirect = MemoryUtil.memAllocInt(sides.size() * 4);
                FloatBuffer position = MemoryUtil.memAllocFloat(drawCount * 3);


                int i = 0;
                for (Entry<Side, List<Vector3i>> entry : sides.entrySet()) {
                    indirect.put(4)
                      .put(entry.getValue().size())
                      .put(4 * entry.getKey().ordinal())
                      .put(i);

                    for (Vector3i offset : entry.getValue()) {
                        position.put(offset.x())
                          .put(offset.y())
                          .put(offset.z());
                    }
                    i += entry.getValue().size();
                }
                indirect.flip();
                position.flip();
                bufferData.put(material, new Triplet<>(sides.size(), indirect, position));
            });

            for (Triplet<Integer, Integer, Integer> buffers : renderData.values()) {
                glDeleteBuffers(buffers.getValue1());
                glDeleteBuffers(buffers.getValue2());
            }
            renderData.clear();
            for (Entry<Material, Triplet<Integer, IntBuffer, FloatBuffer>> buffers : bufferData.entrySet()) {
                IntBuffer indirect = buffers.getValue().getValue1();
                FloatBuffer position = buffers.getValue().getValue2();
                int indirectBuffer = glGenBuffers();
                int positionBuffer = glGenBuffers();

                glBindBuffer(GL_DRAW_INDIRECT_BUFFER, indirectBuffer);
                glBufferData(GL_DRAW_INDIRECT_BUFFER, indirect, GL_STATIC_DRAW);
                glBindBuffer(GL_DRAW_INDIRECT_BUFFER, 0);
                glBindBuffer(GL_ARRAY_BUFFER, positionBuffer);
                glBufferData(GL_ARRAY_BUFFER, position, GL_STATIC_DRAW);
                glBindBuffer(GL_ARRAY_BUFFER, 0);

                MemoryUtil.memFree(indirect);
                MemoryUtil.memFree(position);
                renderData.put(buffers.getKey(), new Triplet<>(buffers.getValue().getValue0(), indirectBuffer, positionBuffer));
            }
        }

        @Override
        public void map() {
            final Queue<Pair<Vector3i, Block>> processingQueue = new ArrayDeque<>();
            final Set<Vector3i> visitedBlocks = new HashSet<>();

            Chunk chunk = server.getChunk(getChunkPos());
            if (chunk == null) return;
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
            requestUpload();
        }

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
    }
}
