package game.mechanics.blocks;

import game.util.Util;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

public class Material {
    private final String imgPath;
    private int texture;
    private boolean compiled;

    private static final Map<String, Material> loadedMaterials;

    static {
        loadedMaterials = new HashMap<>();
    }

    public static Material getInstance(String materialName) {
        if (!loadedMaterials.containsKey(materialName)) loadedMaterials.put(materialName, new Material(materialName));
        return loadedMaterials.get(materialName);
    }

    private Material(String imgPath) {
        this.imgPath = imgPath;
        this.texture = 0;
        this.compiled = false;
    }

    public void bindTexture() {
        if (!compiled) {
            texture = Util.genTexture(imgPath);
            compiled = true;
        }

        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void loseTexture() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
