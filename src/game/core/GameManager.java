package game.core;

import game.assets.BlockFrame;
import game.assets.Callback;
import game.assets.ui_elements.Hotbar;
import game.core.rendering.RenderUtils;
import game.core.server.*;
import game.core.modding.worldgen.WorldGenerator;
import game.mechanics.entities.Entity;
import game.mechanics.entities.Player;
import game.core.rendering.Renderer;
import game.main.Main;
import game.core.rendering.IndirectRenderer;
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

public class GameManager implements Client {
    /**
     * calculated, so the maximum height of the jump is 1.25f blocks
     */
    public static final Vector3f JUMP_VELOCITY = new Vector3f(0, 0, 4.95f);
    public static final Vector3f GRAV_ACCEL = new Vector3f(0, 0, -9.81f);

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
        this.renderer = new IndirectRenderer(this.server);
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

        scrollCallback = (window, xOffset, yOffset) -> {
            if (yOffset > 0) {
                hotbar.incSelect();
            } else if (yOffset < 0) {
                hotbar.decSelect();
            }
        };

        Main.getActiveWindow().setScrollCallback(scrollCallback);
    }

    public void render() {
        if (currentChunk == null || !currentChunk.equals(player.getCurrentChunk())) {
            currentChunk = player.getCurrentChunk();
            renderer.updateChunks(currentChunk, 5, false);
        }
        escCallback.check();

        if (!pauseHandler.isActive()) {
            player.update(this);
        }

        Matrix4f matrixPV = player.getViewMatrix(getProjMatrix());
        renderer.draw(matrixPV);
        player.draw(matrixPV);

        Vector3i firstBlock = Ray.findFirstBlock(player, server, 4);
        if (firstBlock != null) BlockFrame.draw(matrixPV, new Vector3f(firstBlock));
        Vector3i beforeBlock = Ray.beforeFirstBlock(player, server, 4);
        if (beforeBlock != null) BlockFrame.draw(matrixPV, new Vector3f(beforeBlock));

        if (debug.isVisible()) RenderUtils.drawChunk(matrixPV, player.getCurrentChunk());

        gameUI.render();
        debug.render();
        pauseHandler.render();
    }


    public static GameManager joinGame(String addr, int port) throws IOException {
        return new GameManager(new ServerConnection(addr, port));
    }

    public static GameManager demoGame() {
        return new GameManager(World.demoWorld());
    }

    public static GameManager makeGame(String worldName, WorldGenerator generator) {
        return new GameManager(World.makeGame(worldName, generator));
    }

    public static GameManager openGame(String worldName) {
        return new GameManager(World.loadGame(worldName));
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
    public void updateBlock(Vector3i block) {
        renderer.updateBlock(block);
    }

    @Override
    public void loadChunk(int cX, int cY, int cZ) {
        renderer.loadChunk(new Vector3i(cX, cY, cZ));
    }

    @Override
    public void dropChunk(int cX, int cY, int cZ) {
        renderer.dropChunk(new Vector3i(cX, cY, cZ));
    }

    public void closeGame() {
        try {
            server.close();
        } catch (IOException e) {
            Main.setError(e);
        }
    }

    public Matrix4f getProjMatrix() {
        int[] winW = new int[1], winH = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), winW, winH);

        float fov = (float) (Main.getSettings().getFOV()/180f * Math.PI);

        return new Matrix4f().perspective(fov, (float) winW[0] / winH[0], 0.1f, 10000f);
    }

    public Hotbar getHotbar() {
        return hotbar;
    }

    public void crash() {}
}