package game.assets.mcui;

import game.assets.event.EventGenerator;
import game.assets.overlays.Overlay;
import org.joml.Matrix4f;

public class ContentRoot {
    private Component root;
    private final EventGenerator generator;

    public ContentRoot(Component root) {
        this.root = root;
        this.generator = new EventGenerator(this);
    }

    public ContentRoot() {
        this(null);
    }

    public Component getRoot() {
        return root;
    }

    public void setRoot(Component root) {
        this.root = root;
    }

    public void render() {
        if (root != null) {
            Matrix4f matrix4f = Overlay.make2DMatrix();
            root.render(matrix4f);
        }
    }

    public void render(Matrix4f matrix) {
        if (root != null)
            root.render(matrix);
    }

    public Component pick(double x, double y) {
        return root != null ? root.pick(x, y) : null;
    }

    public Component getFocusedElement() {
        return null;
    }

    public EventGenerator getGenerator() {
        return generator;
    }
}
