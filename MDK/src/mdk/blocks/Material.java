package mdk.blocks;

import java.util.HashMap;
import java.util.Map;

public class Material {
    private final String imgPath;

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
    }

    public String getImgPath() {
        return this.imgPath;
    }
}
