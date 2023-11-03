package game.core;

import game.assets.BlockFrame;
import game.assets.Callback;
import game.assets.menus.PauseHandler;
import game.assets.overlays.Debug;
import game.assets.overlays.GameUI;
import game.core.rendering.IndirectRenderer;
import game.core.rendering.RenderUtils;
import game.core.rendering.Renderer;
import game.core.server.Client;
import game.core.server.Server;
import game.core.server.World;
import game.core.server.connect.ServerConnection;
import game.main.Main;
import game.mechanics.entities.Entity;
import game.mechanics.entities.Player;
import game.mechanics.entities.User;
import game.mechanics.physics.Physics;
import game.util.Ray;

import game.window.KeyCallback;
import game.window.WindowFocusCallback;
import game.window.WindowSizeCallback;
import mdk.worldgen.WorldGenerator;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.joml.Vector4f;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.glClearColor;

public class GameManager implements Client {
    public static final Physics physics = new Physics();

    private final Player player;
    private final User user;
    private final Server server;

    private final PauseHandler pauseHandler;
    private final GameUI gameUI;
    private final Debug debug;
    private final Renderer renderer;
    private Vector3i currentChunk;
    private Thread physicsThread;
    private volatile boolean disabled = false;

    private final WindowFocusCallback focusCallback;
    private final WindowSizeCallback sizeCallback;
    private final KeyCallback pauseListener;

    private GameManager(Server server) {
        super();

        this.user = new User();
        this.server = server;
        this.renderer = new IndirectRenderer(this.server);
        this.player = new Player(this.server);
        this.gameUI = new GameUI(this.player);
        this.debug = new Debug(this.server, this.player, this.renderer);

        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glClearColor(0f, 0.5f, 1f, 1f);

        server.registerClient(this);

        pauseHandler = new PauseHandler(player::reset, player::reset);

        pauseListener = (key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                if (!pauseHandler.isEnabled())
                    pauseHandler.enable();
                else
                    pauseHandler.disable();
            }

        };
        Main.getActiveWindow().addKeyCallback(pauseListener);
        focusCallback = focused -> {
            if (!focused)
                pauseHandler.enable();
        };
        Main.getActiveWindow().addWindowFocusCallback(focusCallback);
        sizeCallback = (width, height) -> {
            System.out.printf("Resizing to (%d, %d)\n", width, height);
            player.reset();
        };
        Main.getActiveWindow().addWindowSizeCallback(sizeCallback);
    }

    public void render() {
        if (currentChunk == null || !currentChunk.equals(player.getCurrentChunk())) {
            currentChunk = player.getCurrentChunk();
            renderer.updateChunks(currentChunk, 5, false);
            server.updateChunks(currentChunk, 5, false);
        }
//        escCallback.check();
        update();

        Matrix4f matrixPV = player.getViewMatrix(getProjMatrix());
        renderer.draw(matrixPV);
        player.draw(matrixPV);

        Vector3i firstBlock = Ray.findFirstBlock(player, server, 4);
        if (firstBlock != null) BlockFrame.draw(matrixPV, new Vector3f(firstBlock));
        Vector3i beforeBlock = Ray.beforeFirstBlock(player, server, 4);

        if (beforeBlock != null) {
            if (!player.collides(beforeBlock)) BlockFrame.draw(matrixPV, new Vector3f(beforeBlock));
            else BlockFrame.draw(matrixPV, new Vector3f(beforeBlock), new Vector4f(1, 0, 0, 1));
        }

        if (debug.isVisible()) RenderUtils.drawChunk(matrixPV, player.getCurrentChunk());

        gameUI.render();
        debug.render();
        pauseHandler.render();
    }

    public void update() {
        if (!pauseHandler.isEnabled()) {
            player.update();
        }
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

    private void openPauseMenu() {

    }

    public void closeGame() {
        try {
            Main.getActiveWindow().delWindowFocusCallback(focusCallback);
            Main.getActiveWindow().delWindowSizeCallback(sizeCallback);
            Main.getActiveWindow().delKeyCallback(pauseListener);
            server.close();
            player.close();
            pauseHandler.cleanup();
        } catch (IOException e) {
            Main.setError(e);
        }
        System.err.println("Game Manager closed");
    }

    public static Matrix4f getProjMatrix() {
        float fov = (float) (Main.getSettings().getFOV()/180f * Math.PI);

        return new Matrix4f().perspective(fov, Main.getActiveWindow().getAspectRatio(), 0.1f, 10000f);
    }

    public void crash() {}

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalizing " + this);
    }
}