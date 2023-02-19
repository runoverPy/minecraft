package game.assets.mcui;

import org.joml.Matrix4f;

public class LinearContainer extends Container {
    @Override
    public void render(Matrix4f matrix4f, int cornerX, int cornerY) {
        int y = 0;
        for (Component child : children) {
            child.render(matrix4f, cornerX, cornerY + y);
            y += child.getHeight();
        }
    }
}
