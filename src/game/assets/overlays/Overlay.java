package game.assets.overlays;

import game.main.Main;
import game.assets.Scene;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;

public abstract class Overlay extends Scene {
    public static final float FOV = (float) Math.PI / 2;

    public static Matrix4f make2DMatrix() {
        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        return new Matrix4f()
                .perspective(FOV, aspectRatio, 0.01f, 100f)
                .lookAt(0, 0, 0, 0, 0, -1, 0, 1, 0);
    }
}
