import mdk.blocks.SolidBlock;

public class Grass extends SolidBlock {
    public Grass() {
        super("/img/grass_block.png");
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
