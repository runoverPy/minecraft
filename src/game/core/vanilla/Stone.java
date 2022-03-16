package game.core.vanilla;

import game.mechanics.blocks.base.SolidBlock;

public class Stone extends SolidBlock {
    public Stone() {
        super("/img/directions.png");
    }

    @Override
    public boolean isBreakable() {
        return true;
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
