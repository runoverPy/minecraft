package game.mechanics.blocks;

import game.core.rendering.RenderingMode;

public enum Phase {
    GAS(RenderingMode.NONE),
    LIQUID(RenderingMode.LIQUID),
    SOLID(RenderingMode.SURFACE);

    private final RenderingMode mode;

    Phase(RenderingMode mode) {
        this.mode = mode;
    }

    public RenderingMode getRenderingMode() {
        return mode;
    }
}
