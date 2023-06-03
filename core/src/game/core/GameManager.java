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

import game.window.GLFWWindow;
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
    private final Callback escCallback;
    private final Renderer renderer;
    private Vector3i currentChunk;



    private GameManager(Server server) {
        super();
        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        this.user = new User();
        this.server = server;
        this.renderer = new IndirectRenderer(this.server);
        this.player = new Player(this.server);
        this.gameUI = new GameUI(this.player);
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
    }

    public void render() {
        if (currentChunk == null || !currentChunk.equals(player.getCurrentChunk())) {
            currentChunk = player.getCurrentChunk();
            renderer.updateChunks(currentChunk, 5, false);
        }
        escCallback.check();
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
        if (!pauseHandler.isActive()) {
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

    public void closeGame() {
        try {
            server.close();
        } catch (IOException e) {
            Main.setError(e);
        }
    }

    public static Matrix4f getProjMatrix() {
        float fov = (float) (Main.getSettings().getFOV()/180f * Math.PI);

        return new Matrix4f().perspective(fov, Main.getActiveWindow().getAspectRatio(), 0.1f, 10000f);
    }

    public void crash() {}
}