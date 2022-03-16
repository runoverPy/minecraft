package game.main;

import game.util.Image;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GLFWWindow {
    private final long window;
    public volatile int width, height;
    private boolean fullscreen;

    public GLFWWindow(String title, long monitor, boolean fullscreen) {
        GLFWVidMode mode = glfwGetVideoMode(monitor);
        assert mode != null;
        this.fullscreen = fullscreen;
        if (fullscreen) {
            this.width = mode.width();
            this.height = mode.height();
            window = glfwCreateWindow(width, height, title, monitor, NULL);
        } else {
            this.width = mode.width() / 2;
            this.height = mode.height() / 2;
            window = glfwCreateWindow(width, height, title, NULL, NULL);
            glfwSetWindowPos(window, mode.width() / 4, mode.height() / 4);
        }

        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Fenster konnte nicht initialisiert werden");
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_F11 && action == GLFW_RELEASE) {
                System.out.println("swapping fullscreen");
                this.fullscreen = !this.fullscreen;
                if (this.fullscreen) {
                    glfwSetWindowMonitor(
                            window,
                            monitor,
                            0,
                            0,
                            mode.width(),
                            mode.height(),
                            GLFW_DONT_CARE
                    );
                } else {
                    glfwSetWindowMonitor(
                            window,
                            NULL,
                            mode.width() / 4,
                            mode.height() / 4,
                            mode.width() / 2,
                            mode.height() / 2,
                            GLFW_DONT_CARE
                    );
                }
                glfwSwapInterval(1);
            }
        });

        glfwSetWindowSizeCallback(window, (window1, width, height) -> {
            this.width = width;
            this.height = height;
            glViewport(0, 0, width, height);
            glScissor(0, 0, width, height);
        });
    }

    public boolean fullscreen() {
        return fullscreen;
    }

    public void destroy() {
        glfwDestroyWindow(window);
    }

    public long getWindow() {
        return window;
    }

    public void setContext() {
        glfwMakeContextCurrent(window);
    }

    public void setIcon(String fileName) {
        glfwSetWindowIcon(window, Image.collect(Image.loadImage(fileName).getImageBuffer()));
    }

    public void setKeyCallback(GLFWKeyCallbackI cbfun) {
        glfwSetKeyCallback(window, cbfun);
    }

    public void update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void close() {
        glfwSetWindowShouldClose(window, true);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getAspectRatio() {
        return (float) width / height;
    }

    public void setCursorPos(double x, double y) {
        glfwSetCursorPos(window, x, y);
    }

    public Dimension getWindowSize() {
        return new Dimension(width, height);
    }

    public Position<Double> getCursorPos() {
        double[] mouseX = new double[1], mouseY = new double[1];
        glfwGetCursorPos(Main.getWindowPtr(), mouseX, mouseY);
        return new Position<>(mouseX[0], mouseY[0]);
    }

    public void setInputMode(int mode, int value) {
        glfwSetInputMode(window, mode, value);
    }

    public void setOpacity(float opacity) {
        glfwSetWindowOpacity(window, opacity);
    }

    public int getKey(int key) {
        return glfwGetKey(window, key);
    }

    public int getMouseButton(int button) {
        return glfwGetMouseButton(window, button);
    }

    public static class Dimension {
        private int width, height;

        public Dimension(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public float getAspectRatio() {
            return (float) width / height;
        }
    }

    public static class Position<T extends Number> {
        private T x, y;

        public Position(T x, T y) {
            this.x = x;
            this.y = y;
        }

        public T getX() {
            return x;
        }

        public T getY() {
            return y;
        }
    }
}
