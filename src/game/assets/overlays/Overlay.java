package game.assets.overlays;

import game.main.Main;
import org.joml.Matrix4f;

public abstract class Overlay {
    public static final float fieldOfView = (float) Math.PI / 2;

    public static Matrix4f make2DMatrix() {
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        return new Matrix4f()
          .perspective(fieldOfView, aspectRatio, 0.01f, 100f)
          .lookAt(0, 0, 0, 0, 0, -1, 0, 1, 0);
    }
}
