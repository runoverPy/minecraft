package game.assets.ui_elements;

import game.assets.overlays.Overlay;
import game.main.Main;
import org.joml.Matrix4f;

public class ContentsRoot implements Component {
    private ChildBox rootComponent;

    public ContentsRoot(ChildBox rootComponent) {
        this.rootComponent = rootComponent;
    }

    public ContentsRoot() {
        this.rootComponent = null;
    }

    public Component getRoot() {
        return rootComponent;
    }

    public void setRoot(ChildBox rootComponent) {
        this.rootComponent = rootComponent;
        rootComponent.setParent(this);
    }

    public void render() {
        render(Overlay.make2DMatrix());
    }

    public void render(Matrix4f matrix4f) {
        if (rootComponent != null) rootComponent.draw(1, matrix4f);
        else System.out.println("No root component attached");
    }

    @Override
    public int getWidth() {
        return Main.getActiveWindow().getWidth();
    }

    @Override
    public int getHeight() {
        return Main.getActiveWindow().getHeight();
    }

    @Override
    public int getWidth(int pxScale) {
        return getWidth() * pxScale;
    }

    @Override
    public int getHeight(int pxScale) {
        return getHeight() * pxScale;
    }

    @Override
    public int getCornerX(int pxScale) {
        return 0;
    }

    @Override
    public int getCornerY(int pxScale) {
        return 0;
    }

    @Override
    public Component getTopComponent(int x, int y, int pxScale) {
        return rootComponent.getTopComponent(x, y, pxScale);
    }
}
