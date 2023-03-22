import mdk.blocks.SolidBlock;

public class StoneBricks extends SolidBlock {
    public StoneBricks() {
        super("/img/stone_bricks.png");
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
