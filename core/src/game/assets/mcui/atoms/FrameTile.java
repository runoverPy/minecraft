package game.assets.mcui.atoms;

import game.assets.mcui.PixelComponent;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class FrameTile extends PixelComponent {
    private final ColorTile top, bot, lft, rgt;
    private final Vector4f frameColor;
    private int thickness;

    public FrameTile() {
        this(new Vector4f(), 1);
    }

    public FrameTile(Vector4f frameColor, int thickness) {
        this.frameColor = frameColor;
        this.thickness = thickness;
        top = new ColorTile(frameColor);
        top.setParent(this);
        bot = new ColorTile(frameColor);
        bot.setParent(this);
        lft = new ColorTile(frameColor);
        lft.setParent(this);
        rgt = new ColorTile(frameColor);
        rgt.setParent(this);
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
        layout();
    }

    public Vector4f getFrameColor() {
        return new Vector4f(frameColor);
    }

    public void setFrameColor(Vector4f frameColor) {
        this.frameColor.set(frameColor);
    }

    @Override
    public void layout() {
        top.setPxSize(getPxWidth(), getThickness());
        bot.setPxSize((getPxWidth() - 2 * getThickness()), getThickness());
        bot.setLayoutPos((getThickness() * getPxScale()), (getHeight() - getThickness() * getPxScale()));
        lft.setPxSize(getThickness(), getPxHeight() - getThickness());
        lft.setLayoutPos(0, (getThickness() * getPxScale()));
        rgt.setPxSize(getThickness(), getPxHeight() - getThickness());
        rgt.setLayoutPos((getWidth() - getThickness() * getPxScale()), (getThickness() * getPxScale()));
    }

    @Override
    public void render(Matrix4f matrix) {
//        layoutIfScaleChanged();
        top.render(matrix);
        bot.render(matrix);
        lft.render(matrix);
        rgt.render(matrix);
    }
}
