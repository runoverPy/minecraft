package game.main;

import game.core.GLFWWindow;
import game.assets.menus.ErrorScreen;
import game.assets.text.ProportionalFont;
import game.core.GameManager;

import game.assets.menus.MenuHandler;
import game.core.GameRuntime;
import game.core.settings.DefaultGeneralSettings;
import game.core.settings.GeneralSettings;
import game.core.vanilla.Vanilla;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.glfw.GLFW.*;

public class Main {
    private static GLFWWindow _window;
    private static int vao;
    private static ProportionalFont font;
    private static GeneralSettings settings;
    private static GameManager game;
    private static MenuHandler mainMenu;

    public static final Path HERE = Paths.get(Main.class.getResource("Main.class").getPath()); // Paths.get(".").normalize().toAbsolutePath(); // new File(".").toPath().normalize().toAbsolutePath();

    public static void main(String[] args) throws IOException {

        setup();
        exec();
    }

    private static void setup() {
        if (!glfwInit()) {
            throw new RuntimeException("GLFW konnte nicht initialisiert werden");
        }

        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
        glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, GLFW_TRUE);

        long monitor = glfwGetPrimaryMonitor();

        String title = "HELP IM STUCK IN MINECRAFT";

        boolean fullscreen = false;

        _window = new GLFWWindow(title, monitor, fullscreen);
        _window.setContext();
        _window.setIcon("/img/MeinCraft.png");

        GL.createCapabilities();
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        glPolygonOffset(1, 1);
        glLineWidth(2);

        glEnable(GL_BLEND);
        glEnable(GL_POLYGON_OFFSET_FILL);
        glEnable(GL_LINE_SMOOTH);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glEnable(GL_SCISSOR_TEST);
        glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE);
        GameRuntime.setInstance(new Vanilla());
        mainMenu = MenuHandler.mainMenu();
        font = new ProportionalFont();
        settings = new DefaultGeneralSettings();
    }

    private static void exec() {
        // rendering loop
        while (!_window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            if (game != null) game.render();
            else mainMenu.render();
            _window.update();
        }
        // cleanup
        _window.destroy();
        glfwTerminate();
    }

    public static GLFWWindow getActiveWindow() {
        return _window;
    }

    public static long getWindowPtr() {
        return _window.getWindow();
    }

    public static void openGame(GameManager game) {
        if (Main.game == null) {
            Main.game = game;
        }
    }

    public static void closeGame() {
        if (game != null) {
            game.closeGame();
            game = null;
            mainMenu = MenuHandler.mainMenu();
        }
    }

    public static void crashGame(Throwable error) {
        if (game != null) {
            game.closeGame();
            game = null;
            setError(error);
        }
    }

    public static void setError(Throwable error) {
        if (mainMenu == null) mainMenu = MenuHandler.mainMenu(error);
        else mainMenu.next(new ErrorScreen(mainMenu, error));
    }

    /**
     * When a fatal error has occurred, and it is not useful to continue running the process
     * this method can be called to crash the program.
     */
    public static void crash(Throwable exception) {
        exception.printStackTrace(System.err);
        System.exit(0xf);
    }

    public static ProportionalFont getFont() {
        return font;
    }

    public static GeneralSettings getSettings() {
        return settings;
    }
}
