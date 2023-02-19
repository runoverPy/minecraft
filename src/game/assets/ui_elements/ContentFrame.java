package game.assets.ui_elements;

import game.main.Main;
import org.joml.Matrix4f;

public class ContentFrame extends ChildBox {
    private final ScrollBarY scrollBar;
    private final Frame frame;

    public ContentFrame(int width, int height, int xOffset, int yOffset, Component parent) {
        super(width, height, xOffset, yOffset, parent);
        this.scrollBar = new ScrollBarY(height, width - 5, 0, this);
        this.frame = new Frame(width - 5, height, 0, 0, this);
    }

    public int length() {
        return frame.length();
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        frame.draw(pxScale, matrix4f);
        scrollBar.draw(pxScale, matrix4f);
    }

    public void addChild(ChildBox childBox) {
        frame.addChild(childBox);
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        return scrollBar.onMouseEvent(event, pxScale) || frame.onMouseEvent(event, pxScale);
    }

    private class Frame extends Container {
        public Frame(int width, int height, int xOffset, int yOffset, Component parent) {
            super(width, height, xOffset, yOffset, parent);
        }

        private int length() {
            int length = 0;
            for (ChildBox childBox : children) {
                length += childBox.getHeight();
            }
            return length;
        }

        @Override
        public int getCornerY(int pxScale) {
            return super.getCornerY(pxScale) - (int) (scrollBar.getValue() * Math.max(length() - getHeight(), 0) * pxScale);
        }

        @Override
        public void draw(int pxScale, Matrix4f matrix4f) {
            int winHeight = Main.getActiveWindow().getHeight();
            try (Scissor ignored = Scissor.cut(
              getCornerX(pxScale),
              (winHeight - super.getCornerY(pxScale) - getHeight(pxScale)),
              getWidth(pxScale),
              getHeight(pxScale)
            )) {
                super.draw(pxScale, matrix4f);
            }
        }
    }

    @Override
    public Component getTopComponent(int x, int y, int pxScale) {
        Component topComponent;
        topComponent = scrollBar.getTopComponent(x, y, pxScale);
        if (topComponent != null) return topComponent;
        return frame.getTopComponent(x, y, pxScale);
    }
}
