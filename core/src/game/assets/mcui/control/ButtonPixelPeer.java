package game.assets.mcui.control;

import game.assets.mcui.Align;
import game.assets.mcui.PixelPeer;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.PixelImageTile;
import game.assets.mcui.asset.TextTile;
import org.joml.Vector4f;

public class ButtonPixelPeer extends PixelPeer {
    private final ColorTile buttonCover;
    private final ColorTile buttonFrame;
    private final PixelImageTile buttonImage;
    private final TextTile description;

    public ButtonPixelPeer(AbstractButton button) {
        buttonCover = new ColorTile(new Vector4f(0, 0, 0, 0.5f));
        buttonFrame = new ColorTile(new Vector4f());
        buttonImage = new PixelImageTile(AbstractButton.filler);
        description = new TextTile(button.getDesc());
        description.setAlign(Align.CENTER);
    }

    @Override
    public void layout() {
        buttonCover.setPxSize(getPxWidth(), getPxHeight());
        buttonFrame.setPxSize(getPxWidth(), getPxHeight());
        buttonImage.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
        buttonImage.setLayoutPos(getPxScale(), getPxScale());
        description.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
        buttonImage.setLayoutPos(getPxScale(), getPxScale());
    }

    @Override
    public void requestSetWidth(int width) {
    }
}
