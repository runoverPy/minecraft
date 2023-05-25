package game.assets.mcui;

import game.main.Main;
import org.joml.Matrix4f;

/**
 * todo convert to interface perhaps?
 */
public abstract class PixelComponent extends Component {
    protected static ItemScale scale = ItemScale.LARGE;
    private int lastPxScale = 0;

    public void layoutIfScaleChanged() {
        int nextPxScale = getPxScale();
        if (lastPxScale != nextPxScale) {
            lastPxScale = nextPxScale;
            layout();
        }
    }

//    @Override
//    public void setWidth(int width) {
//        if (getPxScale() == 0) setPxWidth(0);
//        setPxWidth(width / getPxScale());
//    }
//
//    @Override
//    public void setHeight(int height) {
//        setPxHeight(height / getPxScale());
//    }
//
//    @Override
//    public void setSize(int width, int height) {
//        setPxSize((width / getPxScale()), (height / getPxScale()));
//    }

    public void setPxWidth(int pxWidth) {
        super.setWidth(pxWidth);
    }

    public void setPxHeight(int pxHeight) {
        super.setHeight(pxHeight);
    }

    public void setPxSize(int pxWidth, int pxHeight) {
        super.setSize(pxWidth, pxHeight);
    }

    @Override
    public int getWidth() {
        return super.getWidth() * getPxScale();
    }

    @Override
    public int getHeight() {
        return super.getHeight() * getPxScale();
    }

    public int getAbsWidth() {
        return super.getWidth();
    }

    public int getAbsHeight() {
        return super.getHeight();
    }

    public static void setScale(ItemScale scale) {
        PixelComponent.scale = scale;
    }

    public static ItemScale getScale() {
        return PixelComponent.scale;
    }

    protected static int getPxScale() {
        int winWidth, winHeight;
        winWidth = Main.getActiveWindow().getWidth();
        winHeight = Main.getActiveWindow().getHeight();

        int pxWidth, pxHeight;
        pxWidth = winWidth / scale.getPixels();
        pxHeight = winHeight / scale.getPixels();
        return Math.min(pxWidth, pxHeight);
    }

    @Override
    public void render(Matrix4f matrix) {

    }

    public enum ItemScale {
        SMALL (512),
        MEDIUM (384),
        LARGE (256),
        GIANT (128);

        private final int pixels;

        ItemScale(int pixels) {
            this.pixels = pixels;
        }

        public int getPixels() {
            return pixels;
        }
    }
}
