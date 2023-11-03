package game.window;

import game.util.Image;
import game.util.ImageFile;
import org.lwjgl.glfw.*;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class GLFWWindow {
    private final long window;
    public volatile int width, height;
    private boolean fullscreen;

    private final KeyCallback keyCallback;
    private final WindowSizeCallback windowSizeCallback;

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
        windowFocusCallbackHandler.register();

        keyCallback = (key, scancode, action, mods) -> {
            if (key == GLFW_KEY_F9 && action == GLFW_RELEASE) {
                System.out.println(mouseButtonCallbackHandler);
            }
            if (key == GLFW_KEY_F10 && action == GLFW_RELEASE)
                System.gc();
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
        };
        keyCallbackHandler.insert(keyCallback);

        windowSizeCallback = (width, height) -> {
            this.width = width;
            this.height = height;
            glViewport(0, 0, width, height);
            glScissor(0, 0, width, height);
        };
        windowSizeCallbackHandler.insert(windowSizeCallback);
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
        glfwSetWindowIcon(window, Image.collect(ImageFile.loadImage(fileName).getImageBuffer()));
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


    private abstract static class CallbackHandler<CB extends WindowCallback> {
        private static final ReferenceQueue<WindowCallback> graveyard;

        static {
            graveyard = new ReferenceQueue<>();
            Thread cleaner = new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("clearing weak reference to " + graveyard.remove());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            cleaner.setDaemon(true);
            cleaner.start();
        }

        private final List<CB> callbacks;
        private final Queue<CB> insertions, removals;
        private final AtomicBoolean inUse;
        private volatile boolean dirty = false;

        public CallbackHandler() {
            this.callbacks = new LinkedList<>();
            this.insertions = new ArrayDeque<>();
            this.removals = new ArrayDeque<>();
            this.inUse = new AtomicBoolean(false);
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
                    for (CB next : callbacks) {
                        consumer.accept(next);
                    }
                }
                inUse.set(false);
            }
            if (dirty) {
                if (!inUse.getAndSet(true)) {
                    dirty = false;
                    synchronized (callbacks) {
                        callbacks.addAll(insertions);
                        insertions.clear();
                        callbacks.removeAll(removals);
                        removals.clear();
                    }
                    inUse.set(false);
                }
            }
        }

        @Override
        public String toString() {
            return callbacks.toString();
        }

        /**
         * Called to register callback handler with window.
         * @throws NullPointerException if the window is not initialized yet or is for another reason equal to {@code 0L}
         */
        protected abstract void register();
    }

    /*
     *
     *          CALLBACK stuff
     *
     */

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Object> graveyard = new ReferenceQueue<>();
        class Target { int i; }
        Target target = new Target();
        PhantomReference<Target> weakTarget = new PhantomReference<>(target, graveyard);
        Runnable action = () -> {
            Target target1 = weakTarget.get();
            if (target1 == null) return;
            System.out.println(target1.i++);
        };
        action.run();
        target = null;
        System.gc();
        action.run();
        System.out.println(graveyard.remove(10000).get());
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



    private final WindowFocusCallbackHandler windowFocusCallbackHandler = new WindowFocusCallbackHandler();
    private class WindowFocusCallbackHandler extends CallbackHandler<WindowFocusCallback> implements GLFWWindowFocusCallbackI {
        @Override
        protected void register() {
            glfwSetWindowFocusCallback(window, this);
        }

        @Override
        public void invoke(long window, boolean focused) {
            forEach(callback -> callback.invoke(focused));
        }
    }
    public void addWindowFocusCallback(WindowFocusCallback callback) {
        windowFocusCallbackHandler.insert(callback);
    }
    public void delWindowFocusCallback(WindowFocusCallback callback) {
        windowFocusCallbackHandler.remove(callback);
    }
}
