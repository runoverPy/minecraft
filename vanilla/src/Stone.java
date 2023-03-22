import mdk.blocks.SolidBlock;

public class Stone extends SolidBlock {
    public Stone() {
        super("/img/stone_block.png");
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
