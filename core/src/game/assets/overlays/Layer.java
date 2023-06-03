package game.assets.overlays;

import game.assets.mcui.ContentRoot;
import org.joml.Matrix4f;

/**
 *
 */
public abstract class Layer extends ContentRoot {
    protected LayerHandler handler;
    
    public final boolean isActive() {
        return handler.isLayerActive(this);
    }

    public abstract void render(Matrix4f matrix4f);
}
