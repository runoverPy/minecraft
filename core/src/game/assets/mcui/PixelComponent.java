package game.assets.mcui;

import game.main.Main;

public abstract class PixelComponent extends Component {
    protected static ItemScale scale = ItemScale.LARGE;

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

    protected int getPxScale() {
        int winWidth, winHeight;
        winWidth = Main.getActiveWindow().getWidth();
        winHeight = Main.getActiveWindow().getHeight();

        int pxWidth, pxHeight;
        pxWidth = winWidth / scale.getPixels();
        pxHeight = winHeight / scale.getPixels();
        return Math.min(pxWidth, pxHeight);
    }
}
