package game.assets.mcui.container;

import game.assets.mcui.Align;
import game.assets.mcui.Component;
import game.assets.mcui.PixelComponent;

public class HorizontalContainer extends Container {
    private Align align;
    private int spacing;

    public HorizontalContainer() {
        this.align = Align.TOP_LEFT;
    }

    public HorizontalContainer(Align align) {
        this.align = align;
    }

    @Override
    public int calcWidth() {
        int width = (getChildren().size() - 1) * getSpacing();
        for (Component child : getChildren())
            width += child.getWidth();
        return width;
    }

    @Override
    public int calcHeight() {
        int height = 0;
        for (Component child : getChildren())
            height = Math.max(height, child.getHeight());
        return height;
    }

    @Override
    public void layout() {
        int layoutX = 0;
        for (Component child : getChildren()) {
            child.layout();
            child.setLayoutX(layoutX);
            child.setLayoutY(align.getYOffset(getHeight(), child.getHeight()));
            layoutX += child.getWidth() + getSpacing();
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
