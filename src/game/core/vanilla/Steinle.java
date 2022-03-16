package game.core.vanilla;

import game.mechanics.blocks.base.SolidBlock;

public class Steinle extends SolidBlock {
    public Steinle() {
        super("/img/witzig.png");
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
