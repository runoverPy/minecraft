package game.assets.widgets;

import game.assets.boxes.Box;
import game.util.Image;

public abstract class AbstractButton extends ClickFrame {
    protected static final Image filler = Image.loadImage("/img/stone.png");

    public AbstractButton(int width, int height, int xOffset, int yOffset, Box parent) {
        super(width, height, xOffset, yOffset, parent);
    }
}
