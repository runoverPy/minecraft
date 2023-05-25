package game.assets.overlays;

import org.joml.Matrix4f;

/**
 *
 */
public abstract class Layer {
    protected LayerHandler handler;
    
    public final boolean isActive() {
        return handler.isLayerActive(this);
    }

    public abstract void render(Matrix4f matrix4f);
}
