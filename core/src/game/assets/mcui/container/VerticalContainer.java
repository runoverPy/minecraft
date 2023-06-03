package game.assets.mcui.container;

import game.assets.mcui.Align;
import game.assets.mcui.Component;
import game.assets.mcui.PixelComponent;

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
    public int calcWidth() {
        int width = 0;
        for (Component child : getChildren())
            width = Math.max(width, child.getWidth());
        return width;
    }

    @Override
    public int calcHeight() {
        int height = (getChildren().size() - 1) * getSpacing();
        for (Component child : getChildren())
            height += child.getHeight();
        return height;
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
        return spacing * PixelComponent.getPxScale();
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }
}
