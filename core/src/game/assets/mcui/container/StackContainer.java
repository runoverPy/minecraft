package game.assets.mcui.container;

import game.assets.mcui.Align;
import game.assets.mcui.Component;

public class StackContainer extends Container {
    private Align align;

    public StackContainer() {
        this.align = Align.TOP_LEFT;
    }

    public StackContainer(Align align) {
        this.align = align;
    }

    @Override
    public void layout() {
        for (Component child : getChildren()) { // fixme doesnt align children properly
            child.layout();
            int layoutX = align.getXOffset(getWidth(), child.getWidth());
            child.setLayoutX(layoutX);
            int layoutY = align.getYOffset(getHeight(), child.getHeight());
            child.setLayoutY(layoutY);
        }
    }

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }
}
