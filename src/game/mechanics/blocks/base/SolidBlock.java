package game.mechanics.blocks.base;

import game.mechanics.blocks.Material;
import game.mechanics.blocks.Phase;
import game.core.rendering.RenderingMode;

public abstract class SolidBlock extends BlockBase {
    private final String imgFileName;

    public SolidBlock(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    @Override
    public final RenderingMode getRenderingMode() {
        return RenderingMode.SURFACE;
    }

    @Override
    public final Phase getPhase() {
        return Phase.SOLID;
    }

    @Override
    public final Material getMaterial() {
        return Material.getInstance(imgFileName);
    }
}
