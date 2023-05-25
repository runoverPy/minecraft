package game.assets.mcui.container;

import game.assets.mcui.Component;
import game.assets.mcui.Side;

import java.util.HashMap;
import java.util.Map;

public class AnchorPane extends Container {
    private static final Map<Component, Constraints> componentConstraints = new HashMap<>();

    public AnchorPane() {}

    public static void setAnchor(Component node, Side side, Double value) {
        componentConstraints.computeIfAbsent(node, ignored -> new Constraints()).setConstraint(side, value);
    }

    public static void setTopAnchor(Component node, Double value) {
        setAnchor(node, Side.TOP, value);
    }

    public static void setBottomAnchor(Component node, Double value) {
        setAnchor(node, Side.BOTTOM, value);
    }

    public static void setLeftAnchor(Component node, Double value) {
        setAnchor(node, Side.LEFT, value);
    }

    public static void setRightAnchor(Component node, Double value) {
        setAnchor(node, Side.RIGHT, value);
    }

    public static Double getAnchor(Component node, Side side) {
        return componentConstraints.computeIfAbsent(node, ignored -> new Constraints()).getConstraint(side);
    }

    public static Double getTopAnchor(Component node) {
        return getAnchor(node, Side.TOP);
    }

    public static Double getBottomAnchor(Component node) {
        return getAnchor(node, Side.BOTTOM);
    }

    public static Double getLeftAnchor(Component node) {
        return getAnchor(node, Side.LEFT);
    }

    public static Double getRightAnchor(Component node) {
        return getAnchor(node, Side.RIGHT);
    }

    private static class Constraints {
        private Double topAnchor;
        private Double botAnchor;
        private Double lftAnchor;
        private Double rgtAnchor;

        public void setConstraint(Side side, Double value) {
            switch (side) {
                case TOP -> topAnchor = value;
                case BOTTOM -> botAnchor = value;
                case LEFT -> lftAnchor = value;
                case RIGHT -> rgtAnchor = value;
            }
        }
        public Double getConstraint(Side side) {
            return switch (side) {
                case TOP -> topAnchor;
                case BOTTOM -> botAnchor;
                case LEFT -> lftAnchor;
                case RIGHT -> rgtAnchor;
            };
        }
    }

    @Override
    public void layout() {
        for (Component child : getChildren()) {
            child.layout();

            Double
              top = getTopAnchor(child),
              bot = getBottomAnchor(child),
              lft = getLeftAnchor(child),
              rgt = getRightAnchor(child);

            if (top != null && bot != null) {
                int height = (int) (getHeight() - (top + bot));
                if (height > 0) {
//                    System.out.println("Setting AnchorPane child " + child + " height to " + height);
                    child.setLayoutY(top.intValue());
                    child.setHeight(height);
                }
            } else if (top != null) {
                child.setLayoutY(top.intValue());
            } else if (bot != null) {
                child.setLayoutY(bot.intValue() + child.getHeight());
            }
            if (lft != null && rgt != null) {
                int width = (int) (getWidth() - (lft + rgt));
                if (width > 0) {
//                    System.out.println("Setting AnchorPane child " + child + " width to " + width);
                    child.setLayoutX(lft.intValue());
                    child.setWidth(width);
                }
            } else if (lft != null) {
                child.setLayoutX(lft.intValue());
            } else if (rgt != null)
                child.setLayoutX(getWidth() - (rgt.intValue() + child.getWidth()));
        }
    }
}
