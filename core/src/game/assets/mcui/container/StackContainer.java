package game.assets.mcui.container;

import game.assets.mcui.Align;
import game.assets.mcui.Component;

import java.util.HashMap;
import java.util.Map;

public class StackContainer extends Container {
    private static final Map<Component, Align> componentAlignments = new HashMap<>();

    public static void setAlignment(Component component, Align align) {
        componentAlignments.put(component, align);
    }

    public static Align getAlignment(Component component) {
        return componentAlignments.get(component);
    }

    public static Align getAlignmentOrDefault(Component component, Align alternative) {
        return componentAlignments.getOrDefault(component, alternative);
    }

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
            int layoutX = getAlignmentOrDefault(child, align)
              .getXOffset(getWidth(), child.getWidth());
            child.setLayoutX(layoutX);
            int layoutY = getAlignmentOrDefault(child, align)
              .getYOffset(getHeight(), child.getHeight());
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
