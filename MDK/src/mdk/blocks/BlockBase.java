package mdk.blocks;


public abstract class BlockBase {
    public abstract Material getMaterial();
    public abstract Phase getPhase();

    public abstract boolean isBreakable();
    public abstract boolean isClickable();

    /**
     * The method called when blocks of this type are broken
     */
    public abstract void onBreak();

    /**
     * The method called when blocks of this type are clicked
     */
    public abstract void onClick();
}
