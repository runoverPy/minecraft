package game.assets.mcui.container;

import game.assets.mcui.Align;
import game.assets.mcui.Component;

public class HorizontalContainer extends Container {
    private Align align;

    public HorizontalContainer() {
        this.align = Align.TOP_LEFT;
    }

    public HorizontalContainer(Align align) {
        this.align = align;
    }

    @Override
    public void layout() {
        int layoutX = 0;
        for (Component child : getChildren()) {
            child.layout();
            child.setLayoutX(layoutX);
            child.setLayoutY(align.getYOffset(getHeight(), child.getHeight()));
            layoutX += child.getWidth();
        }
    }

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }
}
