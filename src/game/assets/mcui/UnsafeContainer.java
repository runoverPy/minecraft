package game.assets.mcui;

import org.joml.Matrix4f;

import java.util.List;

public class UnsafeContainer extends Container {
    @Override
    public void render(Matrix4f matrix4f, int cornerX, int cornerY) {
        for (Component child : children) {
            child.render(matrix4f, cornerX, cornerY);
        }
    }
}
