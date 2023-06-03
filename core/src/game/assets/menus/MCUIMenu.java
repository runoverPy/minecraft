package game.assets.menus;

import game.assets.Background;
import game.assets.event.EventRunnable;
import game.assets.mcui.Component;
import game.assets.mcui.ContentRoot;
import game.assets.overlays.Overlay;
import game.main.Main;
import game.window.GLFWWindow;
import game.window.WindowSizeCallback;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

public class MCUIMenu extends ContentRoot implements Menu {
    private static final Background defaultBackground = Background.WHITE;
    private Background background;

    private final WindowSizeCallback resizeCallback = (width, height) -> {
        if (getRoot() != null) getRoot().setSize(width, height);
    };

    public MCUIMenu(Background background) {
        this.background = background;
    }

    @Override
    public void setRoot(Component root) {
        GLFWWindow.Dimension windowSize = Main.getActiveWindow().getWindowSize();
        root.setSize(windowSize.width(), windowSize.height());
        Main.getActiveWindow().addWindowSizeCallback(resizeCallback);
        super.setRoot(root);
    }

    @Override
    public void attach() {
        getGenerator().attachCallbacks(Main.getActiveWindow());
        getRoot().layout();
    }

    @Override
    public void detach() {
        getGenerator().detachCallbacks(Main.getActiveWindow());
    }

    public MCUIMenu() {
        this(defaultBackground);
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background != null ? background : defaultBackground;
    }

    @Override
    public void render() {
        glDisable(GL_DEPTH_TEST);
        Matrix4f matrix = Overlay.make2DMatrix();
        background.render(matrix);
        super.render(matrix);
        glEnable(GL_DEPTH_TEST);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing MCUIMenu");
        super.finalize();
    }
}
