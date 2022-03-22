package game.main;

import game.assets.menus.ErrorScreen;
import game.core.Game;
import game.assets.Scene;

import game.assets.menus.MenuHandler;
import game.core.GameRuntime;
import game.core.vanilla.Vanilla;
import game.mechanics.blocks.Block;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.io.IOException;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.glfw.GLFW.*;

public class Main {
    private static GLFWWindow window;
    private static int vao;
    private static Scene scene;

    public static void main(String[] args) {
        setup();
        loop();
        exit();
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

        window = new GLFWWindow(title, monitor, fullscreen);
        window.setContext();
        window.setIcon("/img/MeinCraft.png");

//        glfwSwapInterval(1);

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
        scene = MenuHandler.mainMenu();
        GameRuntime.getInstance().getBlockRegister().testJSONConversion(new Block("vanilla::steinle"));
    }

    private static void loop() {
        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            scene.render();
            window.update();
        }
    }

    private static void exit() {
        if (!window.fullscreen()) {
            System.out.println("Fading...");
            for (int i = 0; i <= 100; i++) {
                window.setOpacity((100 - i) / 100f);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        window.destroy();
    }

    public static void closeGame() throws IOException {
        if (scene instanceof Game) ((Game) scene).closeGame();
    }

    public static GLFWWindow getActiveWindow() {
        return window;
    }

    public static long getWindowPtr() {
        return window.getWindow();
    }

    public static void setScene(Scene scene) {
        Main.scene = scene;
    }

    public static void setError(Throwable error) {
        setScene(new ErrorScreen(error));
    }
}
