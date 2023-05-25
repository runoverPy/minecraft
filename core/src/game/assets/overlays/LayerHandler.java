package game.assets.overlays;

import java.util.Stack;

public class LayerHandler {
    private final Stack<Layer> layers;

    public LayerHandler() {
        this.layers = new Stack<>();
    }

    boolean isLayerActive(Layer layer) {
        return layers.peek().equals(layer);
    }
}
