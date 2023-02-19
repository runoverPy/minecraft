package game.assets.ui_elements;

import game.main.Main;
import org.joml.Matrix4f;

public class FlexBox extends Container {
    public FlexBox(int width, int height) {
        super(width, height, 0, 0, null);
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        super.draw(pxScale * getPixelScale(), matrix4f);
    }

    @Override
    public int getCornerX(int pxScale) {
        return (Main.getActiveWindow().getWidth() - getWidth() * pxScale) / 2;
    }

    @Override
    public int getCornerY(int pxScale) {
        return (Main.getActiveWindow().getHeight() - getHeight() * pxScale) / 2;
    }

    @Override
    public int getPixelScale() {
        int winWidth, winHeight;
        if (getParent() == null){
            winWidth = Main.getActiveWindow().getWidth();
            winHeight = Main.getActiveWindow().getHeight();
        } else {
            winWidth = getParent().getWidth();
            winHeight = getParent().getHeight();
        }

        int pxWidth, pxHeight;
        pxWidth = winWidth / getWidth();
        pxHeight = winHeight / getHeight();
        return Math.min(pxWidth, pxHeight);
    }

    @Override
    public Component getTopComponent(int x, int y, int pxScale) {
        return super.getTopComponent(x, y, pxScale * getPixelScale());
    }
}