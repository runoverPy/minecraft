package game.mechanics;

import mdk.blocks.BlockBase;
import mdk.blocks.Material;
import mdk.blocks.Phase;

public final class Air extends BlockBase {
    @Override
    public Phase getPhase() {
        return Phase.GAS;
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
