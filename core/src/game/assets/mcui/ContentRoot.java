package game.assets.mcui;

import game.assets.mcui.event.EventTarget;
import game.assets.overlays.Overlay;
import org.joml.Matrix4f;

public class ContentRoot {
    private Component root;

    public void render() {
        Matrix4f matrix4f = Overlay.make2DMatrix();
        root.render(matrix4f, 0, 0);
    }

    public Component pick(double x, double y) {
        return root.pick(x, y);
    }
}
