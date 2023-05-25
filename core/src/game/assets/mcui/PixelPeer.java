package game.assets.mcui;

import org.joml.Matrix4f;

public abstract class PixelPeer extends PixelComponent {
    public abstract void requestSetWidth(int width);

    @Override
    public void render(Matrix4f matrix) {
        layoutIfScaleChanged();
    }
}
