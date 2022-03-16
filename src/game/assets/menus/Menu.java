package game.assets.menus;

import game.assets.Background;
import game.assets.widgets.*;
import game.assets.Callback;
import game.assets.overlays.Overlay;
import game.util.Fn;
import org.joml.Matrix4f;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;

abstract class Menu extends Overlay {
    protected final List<Callback> callbacks;
    private final List<ChildBox> childBoxes;
    protected final Background background;
    protected final FlexBox box;

    public Menu(Background background) {
        this(background, 128, 128);
    }

    public Menu(Background background, int width, int height) {
        super();
        this.background = background;
        this.callbacks = new LinkedList<>();
        this.childBoxes = new LinkedList<>();
        this.box = new FlexBox(width, height);
    }

    public WidgetManager organiser(int width, int height, int vertSep, int horSep) {
        return new WidgetManager(width, height, vertSep, horSep);
    }

    public class WidgetManager implements AutoCloseable {
        public final List<List<Widget>> widgets;
        private final int width, height, vertSep, horSep;


        private WidgetManager(int width, int height, int vertSep, int horSep) {
            this.widgets = new LinkedList<>();
            this.width = width;
            this.height = height;
            this.vertSep = vertSep;
            this.horSep = horSep;
        }

        public void insert(Widget... box) {
            widgets.add(Arrays.asList(box));
        }

        @Override
        public void close() {
            int runningY = (getBox().getHeight(1) - (this.height + this.vertSep) * widgets.size() + this.vertSep) / 2;
            for (List<Widget> row : widgets) {
                if (row.size() != 0) {
                    // Reassign width variable, because width of widgets is dynamic
                    int width = (this.width + this.horSep) / row.size() - this.horSep;
                    int runningX = (getBox().getWidth(1) - this.width) / 2;

                    for (Widget widget : row) {
                        childBoxes.add(widget.create(width, height, runningX, runningY, getBox()));
                        runningX += width + horSep;
                    }
                }
                runningY += height + vertSep;
            }
        }
    }

    @Override
    public final void render() {
        glDisable(GL_DEPTH_TEST);

        int pixelScale = box.getPixelScale();
        Matrix4f matrixPV = make2DMatrix();

        background.render(matrixPV);

        box.draw(matrixPV);

        for (ChildBox childBox : childBoxes) {
            childBox.draw(pixelScale, matrixPV);
        }

        for (Callback callback : callbacks) {
            if (callback.check()) break;
        }

        onDraw(pixelScale, matrixPV);

        glEnable(GL_DEPTH_TEST);
    }

    protected void onDraw(int pxScale, Matrix4f matrix4f) {}

    protected FlexBox getBox() {
        return box;
    }
}
