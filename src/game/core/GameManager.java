package game.core;

import game.assets.BlockFrame;
import game.assets.Callback;
import game.assets.Scene;
import game.assets.widgets.Hotbar;
import game.core.server.Client;
import game.core.server.DemoWorld;
import game.core.server.LocalWorld;
import game.core.server.Server;
import game.core.modding.WorldGenerator;
import game.mechanics.entities.Entity;
import game.mechanics.entities.Player;
import game.core.rendering.Renderer;
import game.main.Main;
import game.core.rendering.UnifiedRenderer;
import game.core.server.connect.ServerConnection;
import game.assets.menus.PauseHandler;
import game.assets.overlays.Debug;
import game.assets.overlays.GameUI;
import game.mechanics.entities.User;
import game.util.Ray;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public class GameManager extends Scene implements Client {
    /**
     * calculated, so the maximum height of the jump is 1.25f blocks
     */
    public static final float JUMP_VELOCITY = 4.95f;
    public static final double GRAV_ACCEL = 9.81;

    private static GameManager game = null;

    private final Player player;
    private final User user;
    private final Server server;

    private final PauseHandler pauseHandler;
    private final GameUI gameUI;
    private final Hotbar hotbar;
    private final Debug debug;
    private final Callback escCallback;
    private final Renderer renderer;
    private Vector3i currentChunk;

    private final GLFWKeyCallbackI hotbarCallback;
    private final GLFWScrollCallbackI scrollCallback;

    private GameManager(Server server) {
        super();
        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        glfwSetScrollCallback(Main.getWindowPtr(), (window, xoffset, yoffset) -> {
            System.out.println("Scroll callback invoked: xOffset = " + xoffset + ", yOffset = " + yoffset);
        });

        this.user = new User();
        this.server = server;
        this.renderer = new UnifiedRenderer(this.server);
        this.player = new Player(this.server);
        this.hotbar = Hotbar.demoHotbar();
        this.gameUI = new GameUI(hotbar);
        this.debug = new Debug(this.server, this.player);

        glClearColor(0f, 0.5f, 1f, 1f);

        server.registerClient(this);

        pauseHandler = new PauseHandler(() -> {
            Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_NORMAL);
            player.reset();
        }, () -> {
            Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);
            player.reset();
        });
        escCallback = new Callback(GLFW_KEY_ESCAPE, () -> {
            if (pauseHandler.isActive()) pauseHandler.deactivate();
            else pauseHandler.activate();
        });

        hotbarCallback = (window, key, scancode, action, mods) -> {
            if ('0' <= key && key <= '9') {
                hotbar.select(key - 49);
            }
        };

        Main.getActiveWindow().setKeyCallback(hotbarCallback);

        scrollCallback = (window, xoffset, yoffset) -> {
            if (yoffset > 0) {
                hotbar.incSelect();
            } else if (yoffset < 0) {
                hotbar.decSelect();
            }
        };

        Main.getActiveWindow().setScrollCallback(scrollCallback);
    }

    public void render() {
        if (currentChunk == null || !currentChunk.equals(player.getCurrentChunk())) {
            currentChunk = player.getCurrentChunk();
            renderer.updateChunks(currentChunk, 3, false);
        }
        escCallback.check();

        if (!pauseHandler.isActive()) {
            player.update(this);
        }

        Matrix4f matrixPV = player.getProjViewMatrix(getProjMatrix());
        renderer.draw(matrixPV);
        Vector3i firstBlock = Ray.findFirstBlock(player, server, 4);
        if (firstBlock != null) BlockFrame.draw(matrixPV, new Vector3f(firstBlock));
        Vector3i beforeBlock = Ray.beforeFirstBlock(player, server, 4);
        if (beforeBlock != null) BlockFrame.draw(matrixPV, new Vector3f(beforeBlock));

        if (debug.isVisible()) Renderer.drawChunk(matrixPV, player.getCurrentChunk());

        gameUI.render();
        debug.render();
        pauseHandler.render();
    }


    public static GameManager joinGame(String addr, int port) throws IOException {
        if (game == null) {
            game = new GameManager(new ServerConnection(addr, port));
        }
        return game;
    }

    public static GameManager testGame() {
        if (game == null) {
            game = new GameManager(new DemoWorld());
        }
        return game;
    }

    public static GameManager openGame(String worldName) {
        if (game == null) {
            game = new GameManager(new LocalWorld());
        }
        return game;
    }

    public static GameManager makeGame(String worldName, long seed) {
        if (game == null) {
            game = new GameManager(new LocalWorld());
        }
        return game;
    }

    public static GameManager makeGame(String worldName, long seed, WorldGenerator generator) {
        if (game != null) throw new IllegalStateException();
        game = new GameManager(new LocalWorld());
        return game;
    }

    @Override
    public void updateBlock(Vector3i block) {
        this.renderer.updateBlock(block);
    }

    @Override
    public void userUpdate(Entity.EntityUpdate update) {
        user.update(update);
    }

    @Override
    public void updateBlock(int x, int y, int z) {
        updateBlock(new Vector3i(x, y, z));
    }

    @Override
    public void loadChunk(int cX, int cY, int cZ) {
        renderer.loadChunk(new Vector3i(cX, cY, cZ));
    }

    @Override
    public void dropChunk(int cX, int cY, int cZ) {
        renderer.dropChunk(new Vector3i(cX, cY, cZ));
    }

    public void closeGame() throws IOException {
        server.close();
    }

    public Matrix4f getProjMatrix() {
        int[] winW = new int[1], winH = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), winW, winH);

        float fov = (float) (Main.getSettings().getFOV()/180f * Math.PI);

        return new Matrix4f().perspective(fov, (float) winW[0] / winH[0], 0.1f, 100f);
    }

    public Hotbar getHotbar() {
        return hotbar;
    }
}