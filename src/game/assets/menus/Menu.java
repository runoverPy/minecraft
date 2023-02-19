package game.assets.menus;

import game.assets.Background;
import game.core.GLFWWindow;
import game.assets.MouseEventGenerator;
import game.assets.ui_elements.ChildBox;
import game.assets.ui_elements.FlexBox;
import game.assets.ui_elements.*;
import game.assets.Callback;
import game.assets.overlays.Overlay;
import game.main.Main;
import org.joml.Matrix4f;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import static org.lwjgl.opengl.GL46.*;

abstract class Menu extends ContentsPane {
    protected final List<Callback> callbacks;
    protected final Background background;
    protected final FlexBox box;
    private final MouseEventGenerator generator;
    private Component lastTopComponent = null;

    public Menu(Background background) {
        this(background, 128, 128);
    }

    public Menu(Background background, int width, int height) {
        this.box = new FlexBox(width, height);
        setRoot(this.box);
        this.callbacks = new LinkedList<>();
        this.background = background;
        this.generator = new MouseEventGenerator();
    }

    protected WidgetOrganizer organiser(int width, int height, int vertSep, int horSep) {
        return new WidgetOrganizer(width, height, vertSep, horSep);
    }

    protected class WidgetOrganizer implements AutoCloseable {
        public final List<List<Widget>> widgets;
        private final int width, height, vertSep, horSep;


        private WidgetOrganizer(int width, int height, int vertSep, int horSep) {
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
            int runningY = (box.getHeight() - (this.height + this.vertSep) * widgets.size() + this.vertSep) / 2;
            for (List<Widget> row : widgets) {
                if (row.size() != 0) {
                    // Reassign width variable, because width of widgets is dynamic
                    int width = (this.width + this.horSep) / row.size() - this.horSep;
                    int runningX = (box.getWidth() - this.width) / 2;

                    for (Widget widget : row) {
                        Menu.this.insert(widget.create(width, height, runningX, runningY, Menu.this));
                        runningX += width + horSep;
                    }
                }
                runningY += height + vertSep;
            }
        }
    }

    public TableOrganizer tableOrganizer(int nCols, int nRows, int rowSize, int colSize) {
        return this.new TableOrganizer(nCols, nRows, rowSize, colSize, 2);
    }

    public TableOrganizer tableOrganizer(int nCols, int nRows, int rowSize, int colSize, int padding) {
        return this.new TableOrganizer(nCols, nRows, rowSize, colSize, padding);
    }

    protected class TableOrganizer {
        private final int nCols, nRows, colSize, rowSize, padding;

        private TableOrganizer(int nCols, int nRows, int rowSize, int colSize, int padding) {
            this.nCols = nCols;
            this.nRows = nRows;
            this.colSize = colSize;
            this.rowSize = rowSize;
            this.padding = padding;
        }

        public void insert(Widget widget, int startCol, int numCols, int startRow, int numRows) {
            int width = numCols * this.colSize - 2 * padding;
            int height = numRows * this.rowSize - 2 * padding;
            int xOffset = (box.getWidth() - colSize * nCols) / 2 + startCol * colSize + padding;
            int yOffset = (box.getHeight() - rowSize * nRows) / 2 + startRow * rowSize + padding;
            Menu.this.insert(widget.create(width, height, xOffset, yOffset, Menu.this));
        }
    }

    public final void render() {
        glDisable(GL_DEPTH_TEST);

        int pixelScale = box.getPixelScale();
        Matrix4f matrixPV = Overlay.make2DMatrix();

        GLFWWindow.Position<Double> currentMousePos = Main.getActiveWindow().getCursorPos();
        Component currentTopComponent = getTopComponent(currentMousePos.getX().intValue(), currentMousePos.getY().intValue(), 1);
        if (currentTopComponent != lastTopComponent) {
            lastTopComponent = currentTopComponent;
//            System.out.println("now hovering over: " + lastTopComponent);
        }

        background.render(matrixPV);
        box.draw(1, matrixPV);

        MouseEvent event = generator.update();
        if (event != null) box.onMouseEvent(event, pixelScale);

        for (Callback callback : callbacks) {
            if (callback.check()) break;
        }

        glEnable(GL_DEPTH_TEST);
    }

    void insert(ChildBox childBox) {
        box.addChild(childBox);
    }
}
