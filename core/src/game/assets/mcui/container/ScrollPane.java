package game.assets.mcui.container;

import game.assets.Scissor;
import game.assets.mcui.Component;
import game.assets.mcui.PixelComponent;
import game.main.Main;
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
        this.scrollBarY = new ScrollBarY();
        scrollBarY.setParent(this);
        this.scrollBarY.setPxWidth(5);
    }

    @Override
    public void layout() {
//        if (content == null) return;
//        boolean
//          xActive = switch (barXActivity) {
//              case NEVER -> false;
//              case SOMETIMES -> content.getWidth() > getWidth();
//              case ALWAYS -> true;
//          },
//          yActive = switch (barYActivity) {
//              case NEVER -> false;
//              case SOMETIMES -> content.getHeight() > getHeight();
//              case ALWAYS -> true;
//          };

        int scrollBarDiameter = 5;
        int pxWidth = PixelComponent.getPxScale();
        frame.setSize(getWidth() - scrollBarDiameter * pxWidth, getHeight());
        scrollBarY.setPxHeight(getPxHeight());
        scrollBarY.setLayoutX(getWidth() - scrollBarDiameter * pxWidth);
    }

    @Override
    public void render(Matrix4f matrix) {
        frame.render(matrix);
        scrollBarY.render(matrix);
    }

    @Override
    public Component pick(double x, double y) {
        Component picked = null;
        picked = frame.pick(x, y);
        if (picked != null) return picked;
        picked = scrollBarY.pick(x, y);
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
            return (int) Math.round(delta * scrollBarX.getValue());
        }

        @Override
        public int getLayoutY() {
            if (content == null) return 0;
            int delta = Math.max(content.getHeight() - getHeight(), 0);
//            System.out.println("content.getHeight() = " + content.getHeight() + "; getHeight() = " + getHeight());
            return (int) -Math.round(delta * scrollBarY.getValue());
        }

        @Override
        public void render(Matrix4f matrix) {
            int winHeight = Main.getActiveWindow().getHeight();
            if (content != null) try (Scissor ignored = Scissor.cut(
              ScrollPane.this.getGlobalX(),
              (winHeight - ScrollPane.this.getGlobalY() - getHeight()),
              getWidth(),
              getHeight()
            )) {
            }
            if (content != null)
                content.render(matrix);
        }
    }

    public enum Activity {
        NEVER, SOMETIMES, ALWAYS
    }
}
