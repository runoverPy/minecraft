package game.core.rendering;

import game.core.server.Server;
import game.mechanics.blocks.Material;
import game.util.Side;
import game.util.Util;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.joml.Matrix4f;
import org.joml.Vector3i;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.lwjgl.opengl.GL46.*;

public class IndirectRenderer extends ChunkRenderer {
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

    protected class IndirectRenderingChunk extends ChunkRenderer.RenderingChunk {
        private final Map<Material, Triplet<Integer, Integer, Integer>> renderData;

        private final UploadOperation upload;

        private IndirectRenderingChunk(Vector3i chunkPos) {
            super(chunkPos);
            this.renderData = new HashMap<>();
            this.upload = new UploadOperation();
        }

        @Override
        public void draw(Matrix4f matrixPV) {
            this.upload.runIfPossible();

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
                    material.bindTexture();
                    glMultiDrawArraysIndirect(GL_TRIANGLE_FAN, 0L, drawCount, 16);
                    material.loseTexture();
                });
            }
        }

        public void update() {
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
            upload.setOperation(() -> upload(bufferData));
        }

        public void upload(Map<Material, Triplet<Integer, IntBuffer, FloatBuffer>> bufferData) {
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
    }
}
