package game.assets.ui_elements.container;

import game.assets.ui_elements.ChildBox;
import game.assets.ui_elements.Component;
import game.assets.ui_elements.MouseEvent;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class Container extends ChildBox {
    protected final List<ChildBox> children;

    public Container(int width, int height, int xOffset, int yOffset, Component parent) {
        super(width, height, xOffset, yOffset, parent);
        this.children = new ArrayList<>();
    }

    public void addChild(ChildBox childBox) {
        childBox.setParent(this);
        children.add(childBox);
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        children.forEach(childBox -> childBox.draw(pxScale, matrix4f));
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        if (!contains(event, pxScale)) return false;
        for (int i = children.size(); i-- > 0;) {
            if (children.get(i).onMouseEvent(event, pxScale)) return true;
        }
        return false;
    }

    @Override
    public Component getTopComponent(int x, int y, int pxScale) {
        Component topComponent = null;
        for (Component c : children) {
            Component currentTopComponent = c.getTopComponent(x, y, pxScale);
            if (currentTopComponent != null) topComponent = currentTopComponent;
        }
        return topComponent;
    }
}
