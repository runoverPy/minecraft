package game.assets.ui_elements;

import game.assets.mcui.Container;
import game.assets.mcui.ContentRoot;
import game.assets.mcui.LinearContainer;
import org.joml.Matrix4f;

public class MCUIPort extends ChildBox {
    private Container mcuiContentRoot;

    public MCUIPort(int width, int height, int xOffset, int yOffset, Component parent, Container root) {
        super(width, height, xOffset, yOffset, parent);
        mcuiContentRoot = root;
    }

    public MCUIPort(int width, int height, int xOffset, int yOffset, Component parent) {
        this(width, height, xOffset, yOffset, parent, new LinearContainer());
    }

    public void setRoot(Container newRoot) {
        this.mcuiContentRoot = newRoot;
    }

    public Container getRoot() {
        return this.mcuiContentRoot;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        mcuiContentRoot.setDimensions(getWidth(), getHeight());
        mcuiContentRoot.render(matrix4f, getCornerX(pxScale), getCornerY(pxScale));
        Scissor scissor = Scissor.cut(getCornerX(pxScale), getCornerY(pxScale), getWidth(pxScale), getHeight(pxScale));
        scissor.close();
    }
}
