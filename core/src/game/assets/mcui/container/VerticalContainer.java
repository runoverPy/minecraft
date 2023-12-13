package game.assets.mcui.container;

import game.assets.mcui.Align;
import game.assets.mcui.Component;

public class VerticalContainer extends Container {
    private Align align;
    private int spacing;
    private boolean fillWidth;

    public VerticalContainer() {
        this.align = Align.TOP_LEFT;
        this.fillWidth = false;
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
            if (fillWidth) {
                child.setWidth(getWidth());
            }
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

    public boolean isFillWidth() {
        return fillWidth;
    }

    public void setFillWidth(boolean fillWidth) {
        this.fillWidth = fillWidth;
    }

    public int getSpacing() {
        return spacing * Component.getPxScale();
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }
}
