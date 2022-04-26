package game.assets.widgets;

import game.assets.boxes.Box;
import game.main.Main;
import org.joml.Matrix4f;

public class FlexBox extends Box {
    public FlexBox(int width, int height) {
        super(width, height, 0, 0);
    }

    public void draw(Matrix4f matrix4f) {
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
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();

        int pxWidth, pxHeight;
        pxWidth = winWidth / getWidth();
        pxHeight = winHeight / getHeight();
        return Math.min(pxWidth, pxHeight);
    }
}