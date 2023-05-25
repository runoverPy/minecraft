package game.assets.mcui.container;

import game.assets.mcui.Align;
import game.assets.mcui.Component;

public class VerticalContainer extends Container {
    private Align align;
    private int spacing;

    public VerticalContainer() {
        this.align = Align.TOP_LEFT;
    }

    public VerticalContainer(Align align) {
        this.align = align;
    }

    @Override
    public void layout() {
        int layoutY = 0;
        for (Component child : getChildren()) {
            child.layout();
            child.setLayoutY(layoutY);
            child.setLayoutX(align.getXOffset(getWidth(), child.getWidth()));
            layoutY += child.getHeight() + getSpacing();
        }
    }

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }
}
