package game.assets.mcui;

import org.joml.Matrix4f;

public class TableContainer extends PixelComponent {
    private int rows, cols;

    public TableContainer(int rowNum, int colNum, int cellWidth, int cellHeight) {
        setDimensions(colNum * cellWidth, rowNum * cellHeight);
        setResizeable(false);
    }

    public boolean insert(int col, int row, int numCols, int numRows, Component component) {
        return false;
    }

    @Override
    public void render(Matrix4f matrix4f, int cornerX, int cornerY) {

    }
}
