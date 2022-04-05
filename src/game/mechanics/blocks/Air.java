package game.mechanics.blocks;

import game.core.modding.BlockBase;
import game.core.rendering.RenderingMode;

public final class Air extends BlockBase {
    @Override
    public Phase getPhase() {
        return Phase.GAS;
    }

    @Override
    public RenderingMode getRenderingMode() {
        return RenderingMode.NONE;
    }

    @Override
    public boolean isBreakable() {
        return false;
    }

    @Override
    public boolean isClickable() {
        return false;
    }

    @Override
    public void onBreak() {}

    @Override
    public void onClick() {}

    @Override
    public Material getMaterial() {
        throw new IllegalStateException("Air has no material");
    }
}
