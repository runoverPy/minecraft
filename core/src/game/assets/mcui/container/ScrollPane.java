package game.assets.mcui.container;

import game.assets.GLUtils;
import game.assets.mcui.Component;
import game.assets.mcui.PixelComponent;
import org.joml.Matrix4f;

public class ScrollPane extends PixelComponent {
    private Component content;
    private Frame frame;
    private final ScrollBarX scrollBarX;
    private Activity barXActivity = Activity.SOMETIMES;
    private final ScrollBarY scrollBarY;
    private Activity barYActivity = Activity.SOMETIMES;

    public ScrollPane() {
        this.frame = new Frame();
        frame.setParent(this);
        this.scrollBarX = new ScrollBarX();
        scrollBarX.setParent(this);
        scrollBarX.setPxHeight(5);
        this.scrollBarY = new ScrollBarY();
        scrollBarY.setParent(this);
        scrollBarY.setPxWidth(5);
    }

    @Override
    public void layout() {
        if (content == null) return;
        int scrollBarDiameter = 5;
        boolean
          xActive = switch (barXActivity) {
            case NEVER -> false;
            case SOMETIMES -> content.getWidth() + scrollBarDiameter > getWidth();
            case ALWAYS -> true;
        },
          yActive = switch (barYActivity) {
              case NEVER -> false;
              case SOMETIMES -> content.getHeight() + scrollBarDiameter > getHeight();
              case ALWAYS -> true;
          };

        scrollBarX.setActive(xActive);
        scrollBarY.setActive(yActive);
        int pxWidth = Component.getPxScale();
        if (xActive && yActive) {
            frame.setSize(getWidth() - scrollBarDiameter * pxWidth, getHeight() - scrollBarDiameter * pxWidth);
            scrollBarX.setPxWidth(getPxWidth() - scrollBarDiameter);
            scrollBarX.setLayoutY(getHeight() - scrollBarDiameter * pxWidth);
            scrollBarY.setPxHeight(getPxHeight() - scrollBarDiameter);
            scrollBarY.setLayoutX(getWidth() - scrollBarDiameter * pxWidth);
        } else if (xActive) {
            frame.setSize(getWidth(), getHeight() - scrollBarDiameter * pxWidth);
            scrollBarX.setPxWidth(getPxWidth());
            scrollBarX.setLayoutY(getHeight() - scrollBarDiameter * pxWidth);
        } else if (yActive) {
            frame.setSize(getWidth() - scrollBarDiameter * pxWidth, getHeight());
            scrollBarY.setPxHeight(getPxHeight());
            scrollBarY.setLayoutX(getWidth() - scrollBarDiameter * pxWidth);
        } else {
            frame.setSize(getWidth(), getHeight());
        }
    }

    @Override
    public void render(Matrix4f matrix) {
        frame.render(matrix);
        scrollBarX.render(matrix);
        scrollBarY.render(matrix);
    }

    @Override
    public Component pick(double x, double y) {
        Component picked;
        picked = scrollBarX.pick(x, y);
        if (picked != null) return picked;
        picked = scrollBarY.pick(x, y);
        if (picked != null) return picked;
        picked = frame.pick(x, y);
        return picked;
    }

    public Component getContent() {
        return content;
    }

    public void setContent(Component content) {
        this.content = content;
        content.setParent(frame);
        layout();
    }

    public void setBarXActivity(Activity barXActivity) {
        this.barXActivity = barXActivity;
        layout();
    }

    public void setBarYActivity(Activity barYActivity) {
        this.barYActivity = barYActivity;
        layout();
    }

    private class Frame extends Container {
        @Override
        public void layout() {}

        @Override
        public int getLayoutX() {
            if (content == null) return 0;
            int delta = content.getWidth() - getWidth();
            return (int) -Math.round(delta * scrollBarX.getValue());
        }

        @Override
        public int getLayoutY() {
            if (content == null) return 0;
            int delta = content.getHeight() - getHeight();
            return (int) -Math.round(delta * scrollBarY.getValue());
        }

        @Override
        public void render(Matrix4f matrix) {
            if (content != null) try (GLUtils.GLOP ignored = GLUtils.scissor(
              ScrollPane.this.getGlobalX(),
              ScrollPane.this.getGlobalY(),
              getWidth(),
              getHeight()
            )) {
                content.render(matrix);
            }
        }
    }

    public enum Activity {
        NEVER, SOMETIMES, ALWAYS
    }
}
