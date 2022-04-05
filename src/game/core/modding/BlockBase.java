package game.core.modding;

import game.mechanics.blocks.Material;
import game.mechanics.blocks.Phase;
import game.core.rendering.RenderingMode;

public abstract class BlockBase {
    public abstract Material getMaterial();
    public abstract Phase getPhase();
    public abstract RenderingMode getRenderingMode();

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
