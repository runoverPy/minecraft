package game.mechanics.rendering;

public enum RenderingMode {
    SURFACE(true),
    LIQUID(true),
    NONE(false),
    TILE(false);

    private final boolean surfaceRendering;

    RenderingMode(boolean surfaceRendering) {
        this.surfaceRendering = surfaceRendering;
    }

    public boolean isSurfaceRendering() {
        return surfaceRendering;
    }
}
