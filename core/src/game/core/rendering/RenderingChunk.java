package game.core.rendering;

import mdk.blocks.Material;
import org.joml.Matrix4f;
import org.joml.Vector3i;

import java.util.HashMap;
import java.util.Map;

public abstract class RenderingChunk {
    protected final Map<Face, Material> faces;
    private final Vector3i chunkPos;

    public RenderingChunk(Vector3i chunkPos) {
        this.chunkPos = chunkPos;
        this.faces = new HashMap<>();
    }

    public abstract void draw(Matrix4f matrixPV);

    public abstract void requestUpload();

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

    public abstract void map();
}
