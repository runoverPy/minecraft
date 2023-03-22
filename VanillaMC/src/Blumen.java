import mdk.blocks.SolidBlock;

public class Blumen extends SolidBlock {
    public Blumen() {
        super("/img/flowers.png");
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
