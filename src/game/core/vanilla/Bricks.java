package game.core.vanilla;

import game.mechanics.blocks.base.SolidBlock;

public class Bricks extends SolidBlock {
    public Bricks() {
        super("/img/bricks.png");
    }

    @Override
    public boolean isBreakable() {
        return true;
    }

    @Override
    public boolean isClickable() {
        return true;
    }

    @Override
    public void onBreak() {

    }

    @Override
    public void onClick() {

    }
}
