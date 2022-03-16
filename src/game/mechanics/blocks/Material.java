package game.mechanics.blocks;

import game.util.Fn;
import game.util.Util;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

public class Material {
    private final int texture;

    private static final Map<String, Material> loadedMaterials;

    static {
        loadedMaterials = new HashMap<>();
    }

    public static Material getInstance(String materialName) {
        if (!loadedMaterials.containsKey(materialName)) loadedMaterials.put(materialName, new Material(materialName));
        return loadedMaterials.get(materialName);
    }

    private Material(String imgPath) {
        this.texture = Util.genTexture(imgPath);
    }

    public void bindTexture() {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void loseTexture() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void withTexture(Fn action) {
        bindTexture();
        action.call();
        loseTexture();
    }
}
