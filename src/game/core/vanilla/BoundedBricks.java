package game.core.vanilla;

import game.core.modding.SolidBlock;

public class BoundedBricks extends SolidBlock {
    public BoundedBricks() {
        super("/img/bricks_bounded.png");
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
