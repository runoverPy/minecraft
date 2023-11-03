package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Component;
import game.assets.mcui.ContentRoot;
import game.assets.overlays.Overlay;
import game.main.Main;
import game.window.GLFWWindow;
import game.window.WindowSizeCallback;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

public class Menu extends ContentRoot {
    private static final Background defaultBackground = Background.WHITE;
    private Background background;

    private final WindowSizeCallback resizeCallback = (width, height) -> {
        if (getRoot() != null) getRoot().setSize(width, height);
    };

    public Menu(Background background) {
        this.background = background;
    }

    public void attach() {
        Main.getActiveWindow().addWindowSizeCallback(resizeCallback);
        GLFWWindow.Dimension windowSize = Main.getActiveWindow().getWindowSize();
        getRoot().setSize(windowSize.width(), windowSize.height());
        getRoot().layout();
    }

    public void detach() {
        Main.getActiveWindow().delWindowSizeCallback(resizeCallback);
    }

    public Menu() {
        this(defaultBackground);
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background != null ? background : defaultBackground;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public void render() {
        glDisable(GL_DEPTH_TEST);
        Matrix4f matrix = Overlay.make2DMatrix();
        background.render(matrix);
        super.render(matrix);
        glEnable(GL_DEPTH_TEST);
    }
}
