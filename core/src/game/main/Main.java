package game.main;

import game.assets.menus.ErrorScreen;
import game.assets.menus.MenuHandler;
import game.assets.text.ProportionalFont;
import game.core.GraphicsEngine;
import game.window.GLFWWindow;
import game.core.GameManager;
import game.core.GameRuntime;
import game.core.settings.GeneralSettings;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public class Main {
    private static GraphicsEngine engine;
    private static GLFWWindow window;

    private static ProportionalFont font;
    private static GeneralSettings settings;
    private static GameRuntime runtime;
    private static GameManager game;
    private static MenuHandler mainMenu;

    public static void main(String[] args) throws IOException {
        setup();
        exec();
    }

    private static void setup() throws FileNotFoundException {
        // init glfw
        if (!glfwInit())
            throw new RuntimeException("GLFW konnte nicht initialisiert werden");
        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
        glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, GLFW_TRUE);

        // init window
        String title = "HELP IM STUCK IN MINECRAFT";
        boolean fullscreen = false;
        window = GLFWWindow.createOnPrimaryMonitor(title, fullscreen);
        window.setContext();
        window.setIcon("/img/MeinCraft.png");

        // init engine
        engine = new GraphicsEngine();
        engine.init();

        // init stuff
        runtime = GameRuntime.setInstance();
        mainMenu = MenuHandler.mainMenu();
        font = new ProportionalFont();
        settings = new GeneralSettings();
    }

    private static void exec() {
        // rendering loop
        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            if (game != null) game.render(); // todo cleanup this
            else mainMenu.render();
            window.update();
        }
        // cleanup
        window.destroy();
        glfwTerminate();
    }

    public static GLFWWindow getActiveWindow() {
        return window;
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

    private static final long startTime = System.currentTimeMillis();
    public static long getStartTime() {
        return startTime;
    }
    public static long getCurrentTime() {
        return System.currentTimeMillis() - startTime;
    }
}
