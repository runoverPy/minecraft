package mdk.blocks;

public abstract class SolidBlock extends BlockBase {
    private final String imgFileName;

    public SolidBlock(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    @Override
    public final Phase getPhase() {
        return Phase.SOLID;
    }

    @Override
    public final Material getMaterial() {
        return Material.getInstance(imgFileName);
    }
}
