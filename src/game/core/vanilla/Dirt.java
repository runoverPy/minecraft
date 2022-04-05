package game.core.vanilla;

import game.core.modding.SolidBlock;

public class Dirt extends SolidBlock {
    public Dirt() {
        super("/img/dirt_block.png");
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
    public void onBreak() {

    }

    @Override
    public void onClick() {

    }
}
