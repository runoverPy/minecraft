package game.mechanics.inventory;

import org.joml.Matrix4f;

public interface Itemizeable {
    boolean onRightClick();
    boolean onLeftClick();

    void drawItem(Matrix4f matrix4f);
    int getStackSize();
}
