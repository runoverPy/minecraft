package game.assets;


import game.main.Main;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

/**
 * The abstract base class for all items on the screen, that consist of large pixels
 */
public abstract class PixelWidget {
    private final int width, height;


    public PixelWidget(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public PixelWidget(int height) {
        this(0, height);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    protected float getSizeForPixels(float scale) {
        int winHeight = Main.getActiveWindow().getHeight();

        int size = (int) (scale / (height * 2) * winHeight);
        return (float) (height * 2) * size / winHeight;
    }
}
