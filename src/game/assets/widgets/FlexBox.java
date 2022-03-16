package game.assets.widgets;

import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class FlexBox extends Box {
    private final List<?> contents;
//    private final ColorBox colorBox;

    public FlexBox(int width, int height) {
        super(width, height, 0, 0);
        this.contents = new ArrayList<>();
//        this.colorBox = new ColorBox(width, height, 0, 0, this, new Vector4f(0.5f, 0.5f, 0.5f, 1));
    }

    public void draw(Matrix4f matrix4f) {
        int pxScale = getPixelScale();
//        colorBox.draw(pxScale, matrix4f);
    }

    @Override
    public int getCornerX(int pxScale) {
        return (Main.getActiveWindow().getWidth() - width * pxScale) / 2;
    }

    @Override
    public int getCornerY(int pxScale) {
        return (Main.getActiveWindow().getHeight() - height * pxScale) / 2;
    }

    @Override
    public int getPixelScale() {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();

        int pxWidth, pxHeight;
        pxWidth = winWidth / width;
        pxHeight = winHeight / height;
        return Math.min(pxWidth, pxHeight); // the monitor size of an image pixel
    }
}