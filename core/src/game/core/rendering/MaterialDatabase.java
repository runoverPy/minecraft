package game.core.rendering;

import mdk.blocks.Material;
import game.util.Util;
import org.lwjgl.opengl.GL46;

import java.util.HashMap;
import java.util.Map;

public class MaterialDatabase {
    private final Map<String, LoadedMaterial> loadedMaterials;

    public MaterialDatabase() {
        this.loadedMaterials = new HashMap<>();
    }

    public void bindMaterial(Material material) {
        String imgPath = material.getImgPath();
        if (!loadedMaterials.containsKey(imgPath)) {
            int texture = Util.genTexture(imgPath);
            loadedMaterials.put(imgPath, new LoadedMaterial(texture));
        }
        LoadedMaterial loadedMaterial = loadedMaterials.get(imgPath);

        GL46.glBindTexture(GL46.GL_TEXTURE_2D, loadedMaterial.texture);
    }

    public void loseMaterial() {
        GL46.glBindTexture(GL46.GL_TEXTURE_2D, 0);
    }

    static class LoadedMaterial {
        public final int texture;

        private LoadedMaterial(int texture) {
            this.texture = texture;
        }
    }
}
