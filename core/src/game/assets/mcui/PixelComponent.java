package game.assets.mcui;

/**
 * todo convert to interface perhaps?
 */
public abstract class PixelComponent extends Component {
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

    public int getPxWidth() {
        return super.getWidth();
    }

    public int getPxHeight() {
        return super.getHeight();
    }

}
