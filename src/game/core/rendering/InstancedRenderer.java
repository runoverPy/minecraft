package game.core.rendering;

import game.core.server.Server;
import game.mechanics.blocks.Material;
import game.util.Side;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector3i;
import org.lwjgl.system.MemoryUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL46.*;

public class InstancedRenderer extends ChunkRenderer {
    private static final int programID;
    private static final int fullTransform;

    static {
        programID = Util.genProgram(
                new Pair<>("/shaders/surface-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/block-fs.glsl", GL_FRAGMENT_SHADER)
        );
        fullTransform = glGetUniformLocation(programID, "fullTransform");
    }

    public InstancedRenderer(Server server) {
        super(server);
    }

    @Override
    public void loadChunk(Vector3i chunk) {
        if (!containsChunk(chunk)) {
            InstancedChunk newChunk = new InstancedChunk(chunk);
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

    public class InstancedChunk extends ChunkRenderer.RenderingChunk {
        private final Map<Material, Map<Side, Pair<Integer, Integer>>> renderData;
        private final UploadOperation upload = new UploadOperation();

        public InstancedChunk(Vector3i chunkPos) {
            super(chunkPos);
            renderData = new HashMap<>();
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
                renderData.forEach((material, sides) -> {
                    material.bindTexture();
                    sides.forEach(((side, objects) -> {
                        int drawCount = objects.getValue0();
                        int positionBuffer = objects.getValue1();

                        glBindBuffer(GL_ARRAY_BUFFER, positionBuffer);
                        glEnableVertexAttribArray(2);
                        glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0L);
                        glVertexAttribDivisor(2, 1);

                        glDrawArraysInstanced(GL_TRIANGLE_FAN, 4 * side.ordinal(), 4, drawCount);
                    }));
                    material.loseTexture();
                });
            }
        }

        @Override
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

            Map<Material, Map<Side, Pair<Integer, FloatBuffer>>> bufferData = new HashMap<>();

            sortedFaces.forEach((material, sideListMap) -> {
                bufferData.put(material, new HashMap<>());
                sideListMap.forEach((side, vectors) -> {
                    FloatBuffer position = MemoryUtil.memAllocFloat(vectors.size() * 3);
                    for (Vector3i offset : vectors) {
                        position.put(offset.x())
                                .put(offset.y())
                                .put(offset.z());
                    }
                    position.flip();
                    bufferData.get(material).put(side, new Pair<>(vectors.size(), position));
                });
            });

            upload.setOperation(() -> upload(bufferData));
        }

        public void upload(Map<Material, Map<Side, Pair<Integer, FloatBuffer>>> bufferData) {}
    }
}
