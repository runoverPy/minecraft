package game.assets.mcui.control;

import game.assets.mcui.Align;
import game.assets.mcui.PixelPeer;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.ImageTile;
import game.assets.mcui.asset.TextTile;
import org.joml.Vector4f;

public class ButtonPixelPeer extends PixelPeer {
    private final ColorTile buttonCover;
    private final ColorTile buttonFrame;
    private final ImageTile buttonImage;
    private final TextTile description;

    public ButtonPixelPeer(AbstractButton button) {
        buttonCover = new ColorTile(new Vector4f(0, 0, 0, 0.5f));
        buttonFrame = new ColorTile(new Vector4f());
        buttonImage = new ImageTile(AbstractButton.filler);
        description = new TextTile(button.getDesc());
        description.setAlign(Align.CENTER);
    }

    @Override
    public void layout() {
        buttonCover.setPxSize(getAbsWidth(), getAbsHeight());
        buttonFrame.setPxSize(getAbsWidth(), getAbsHeight());
        buttonImage.setPxSize((getAbsWidth() - 2), (getAbsHeight() - 2));
        buttonImage.setLayoutPos(getPxScale(), getPxScale());
        description.setPxSize((getAbsWidth() - 2), (getAbsHeight() - 2));
        buttonImage.setLayoutPos(getPxScale(), getPxScale());
    }

    @Override
    public void requestSetWidth(int width) {
    }
}
