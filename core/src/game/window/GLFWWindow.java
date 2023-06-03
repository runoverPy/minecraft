package game.window;

import game.main.Main;
import game.util.Image;
import org.lwjgl.glfw.*;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

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

        keyCallbackHandler.register();
        charCallbackHandler.register();
        mouseButtonCallbackHandler.register();
        scrollCallbackHandler.register();
        cursorPosCallbackHandler.register();
        windowSizeCallbackHandler.register();

        keyCallbackHandler.insert((key, scancode, action, mods) -> {
            if (key == GLFW_KEY_F11 && action == GLFW_RELEASE) {
                this.fullscreen = !this.fullscreen;
                if (this.fullscreen) glfwSetWindowMonitor(
                  window,
                  monitor,
                  0,
                  0,
                  mode.width(),
                  mode.height(),
                  GLFW_DONT_CARE
                );
                else glfwSetWindowMonitor(
                  window,
                  NULL,
                  mode.width() / 4,
                  mode.height() / 4,
                  mode.width() / 2,
                  mode.height() / 2,
                  GLFW_DONT_CARE
                );
                glfwSwapInterval(1);
            }
        });

        windowSizeCallbackHandler.insert((width, height) -> {
            this.width = width;
            this.height = height;
            glViewport(0, 0, width, height);
            glScissor(0, 0, width, height);
        });

        addKeyCallback((key, scancode, action, mods) -> {
            if (key == GLFW_KEY_G)
                System.out.println("G pressed");
        });
    }

    public static GLFWWindow createOnPrimaryMonitor(String title, boolean fullscreen) {
        long primaryMonitor = glfwGetPrimaryMonitor();
        return new GLFWWindow(title, primaryMonitor, fullscreen);
    }

    public boolean fullscreen() {
        return fullscreen;
    }

    public void destroy() {
        glfwDestroyWindow(window);
    }

    public void setContext() {
        glfwMakeContextCurrent(window);
    }

    public void setIcon(String fileName) {
        glfwSetWindowIcon(window, Image.collect(Image.loadImage(fileName).getImageBuffer()));
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
        GLFW.glfwGetCursorPos(window, mouseX, mouseY);
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


    public record Dimension(int width, int height) {}
    public record Position<T extends Number>(T x, T y) {}


    /*
     *
     *          CALLBACK stuff
     *
     */

    private abstract static class CallbackHandler<CB extends WindowCallback> {
        private final List<CB> callbacks;
        private final Queue<CB> insertions, removals;
        private final AtomicBoolean inUse;
        private volatile boolean dirty;

        public CallbackHandler() {
            this.callbacks = new LinkedList<>();
            this.insertions = new ArrayDeque<>();
            this.removals = new ArrayDeque<>();
            this.inUse = new AtomicBoolean(false);
            this.dirty = false;
        }

        public final void insert(CB callback) {
            if (!inUse.getAndSet(true)) {
                synchronized (callbacks) {
                    callbacks.add(callback);
                }
                inUse.set(false);
            } else {
                insertions.add(callback);
                dirty = true;
            }
        }

        public final void remove(CB callback) {
            if (!inUse.getAndSet(true)) {
                synchronized (callbacks) {
                    callbacks.remove(callback);
                }
                inUse.set(false);
            } else {
                removals.add(callback);
                dirty = true;
            }
        }

        protected void forEach(Consumer<CB> consumer) {
            if (!inUse.getAndSet(true)) {
                synchronized (callbacks) {
                    for (CB callback : callbacks) {
                        consumer.accept(callback);
                    }
                }
                inUse.set(false);
            }
            if (dirty) {
                synchronized (callbacks) {
                    callbacks.addAll(insertions);
                    insertions.clear();
                    callbacks.removeAll(removals);
                    removals.clear();
                }
            }
        }

        /**
         * Called to register callback handler with window.
         * @throws NullPointerException if the window is not initialized yet or is for another reason equal to {@code 0L}
         */
        protected abstract void register();
    }


    private final KeyCallbackHandler keyCallbackHandler = new KeyCallbackHandler();
    private class KeyCallbackHandler extends CallbackHandler<KeyCallback> implements GLFWKeyCallbackI {
        @Override
        protected void register() {
            glfwSetKeyCallback(window, this);
        }

        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            forEach(callback -> callback.invoke(key, scancode, action, mods));
        }
    }
    public void addKeyCallback(KeyCallback cbfun) {
        keyCallbackHandler.insert(cbfun);
    }
    public void delKeyCallback(KeyCallback cbfun) {
        keyCallbackHandler.remove(cbfun);
    }


    private final CharCallbackHandler charCallbackHandler = new CharCallbackHandler();
    private class CharCallbackHandler extends CallbackHandler<CharCallback> implements GLFWCharCallbackI {
        @Override
        protected void register() {
            glfwSetCharCallback(window, this);
        }

        @Override
        public void invoke(long window, int codepoint) {
            forEach(callback -> callback.invoke(codepoint));
        }
    }
    public void addCharCallback(CharCallback callback) {
        charCallbackHandler.insert(callback);
    }
    public void delCharCallback(CharCallback callbackI) {
        charCallbackHandler.remove(callbackI);
    }


    private final MouseButtonCallbackHandler mouseButtonCallbackHandler = new MouseButtonCallbackHandler();
    private class MouseButtonCallbackHandler extends CallbackHandler<MouseButtonCallback> implements GLFWMouseButtonCallbackI {
        @Override
        protected void register() {
            glfwSetMouseButtonCallback(window, this);
        }

        @Override
        public void invoke(long window, int button, int action, int mods) {
            forEach(callback -> callback.invoke(button, action, mods));
        }
    }
    public void addMouseButtonCallback(MouseButtonCallback callback) {
        mouseButtonCallbackHandler.insert(callback);
    }
    public void delMouseButtonCallback(MouseButtonCallback callback) {
        mouseButtonCallbackHandler.remove(callback);
    }


    private final ScrollCallbackHandler scrollCallbackHandler = new ScrollCallbackHandler();
    private class ScrollCallbackHandler extends CallbackHandler<ScrollCallback> implements GLFWScrollCallbackI {
        @Override
        protected void register() {
            glfwSetScrollCallback(window, this);
        }
        @Override
        public void invoke(long window, double xOffset, double yOffset) {
            forEach(callback -> callback.invoke(xOffset, yOffset));
        }

    }
    public void addScrollCallback(ScrollCallback callback) {
        scrollCallbackHandler.insert(callback);
    }
    public void delScrollCallback(ScrollCallback callback) {
        scrollCallbackHandler.remove(callback);
    }


    private final CursorPosCallbackHandler cursorPosCallbackHandler = new CursorPosCallbackHandler();
    private class CursorPosCallbackHandler extends CallbackHandler<CursorPosCallback> implements GLFWCursorPosCallbackI {
        @Override
        protected void register() {
            glfwSetCursorPosCallback(window, this);
        }
        @Override
        public void invoke(long window, double xpos, double ypos) {
            forEach(callback -> callback.invoke(xpos, ypos));
        }

    }
    public void addCursorPosCallback(CursorPosCallback callback) {
        cursorPosCallbackHandler.insert(callback);
    }
    public void delCursorPosCallback(CursorPosCallback callback) {
        cursorPosCallbackHandler.remove(callback);
    }


    private final WindowSizeCallbackHandler windowSizeCallbackHandler = new WindowSizeCallbackHandler();
    private class WindowSizeCallbackHandler extends CallbackHandler<WindowSizeCallback> implements GLFWWindowSizeCallbackI {
        @Override
        protected void register() {
            glfwSetWindowSizeCallback(window, this);
        }

        @Override
        public void invoke(long window, int width, int height) {
            forEach(callback -> callback.invoke(width, height));
        }
    }
    public void addWindowSizeCallback(WindowSizeCallback callback) {
        windowSizeCallbackHandler.insert(callback);
    }
    public void delWindowSizeCallback(WindowSizeCallback callback) {
        windowSizeCallbackHandler.remove(callback);
    }
}
