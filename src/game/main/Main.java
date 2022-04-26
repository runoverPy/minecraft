package game.main;

import game.assets.GLFWWindow;
import game.assets.menus.ErrorScreen;
import game.assets.text.ProportionalFont;
import game.core.GameManager;
import game.assets.Scene;

import game.assets.menus.MenuHandler;
import game.core.GameRuntime;
import game.core.settings.DefaultGeneralSettings;
import game.core.settings.GeneralSettings;
import game.core.vanilla.Vanilla;
import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.io.IOException;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.glfw.GLFW.*;

public class Main {
    private static GLFWWindow _window;
    private static int vao;
    private static Scene scene;
    private static ProportionalFont propFont;
    private static GeneralSettings settings;
    private static GameRuntime runtime;

    private GLFWWindow window;
//    private GameRuntime runtime;
    private ProportionalFont font;

    public static void getResource(String resourceName) {}

    public static void main(String[] args) throws IOException, ParseException {
        setup();
        loop();
        exit();
    }

    private static void setup() throws IOException, ParseException {
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

        try {
            propFont = new ProportionalFont();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        GameRuntime.setInstance(new Vanilla());
        settings = new DefaultGeneralSettings();
        mainMenu();
    }

    private static void loop() {
        while (!_window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            scene.render();
            _window.update();
        }
    }

    private static void exit() {
        if (!_window.fullscreen()) {
            for (int i = 0; i <= 100; i++) {
                _window.setOpacity((100 - i) / 100f);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        _window.destroy();
        glfwTerminate();
    }

    public static void closeGame() throws IOException {
        if (scene instanceof GameManager) ((GameManager) scene).closeGame();
    }

    public static GLFWWindow getActiveWindow() {
        return _window;
    }

    public static long getWindowPtr() {
        return _window.getWindow();
    }

    public static void setScene(Scene scene) {
        Main.scene = scene;
    }

    public static void crash(Throwable exception) {
        exception.printStackTrace(System.err);
        System.exit(0xf);
    }

    public static void setError(Throwable error) {
        setScene(new ErrorScreen(error));
    }

    public static void mainMenu() {
        setScene(MenuHandler.mainMenu());
    }

    public static ProportionalFont getPropFont() {
        return propFont;
    }

    public static GeneralSettings getSettings() {
        return settings;
    }
}
