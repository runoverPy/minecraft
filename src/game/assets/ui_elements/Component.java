package game.assets.ui_elements;

public interface Component {
    int getWidth();

    int getHeight();

    int getWidth(int pxScale);

    int getHeight(int pxScale);

    int getCornerX(int pxScale);

    int getCornerY(int pxScale);

    Component getTopComponent(int x, int y, int pxScale);
}
