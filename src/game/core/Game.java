package game.core;

import game.assets.BlockFrame;
import game.assets.Callback;
import game.assets.Scene;
import game.mechanics.WorldGenerator;
import game.mechanics.entities.Player;
import game.mechanics.rendering.Renderer;
import game.main.Main;
import game.mechanics.rendering.UnifiedRenderer;
import game.core.server.core.*;
import game.core.server.connect.ServerConnection;
import game.assets.menus.PauseHandler;
import game.assets.overlays.Debug;
import game.assets.overlays.GameUI;
import game.util.Ray;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public class Game extends Scene implements Client {
    /**
     * calculated, so the maximum height of the jump is 1.25f blocks
     */
    public static final float JUMP_VELOCITY = 4.95f;
    public static final double GRAV_ACCEL = 9.81;

    private static Game game = null;

    private final Player player;
    private final Server server;
    private final PauseHandler pauseHandler;
    private final GameUI gameUI;
    private final Debug debug;
    private final Callback escCallback;
    private final Renderer renderer;
    private Vector3i currentChunk;

    private Game() {
        this(new DemoWorld());
    }

    private Game(Server server) {
        super();
        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        this.server = server;
        this.renderer = new UnifiedRenderer(this.server);
        this.player = new Player(this.server);
        this.gameUI = new GameUI();
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
            renderer.updateChunks(currentChunk, 4, false);
        }
        escCallback.check();

        if (!pauseHandler.isActive()) {
            player.update();
        }
        
        Matrix4f matrixPV = player.getProjectionView();
        renderer.draw(matrixPV);
        Vector3i firstBlock = Ray.findFirstBlock(player, server, 4);
        if (firstBlock != null) BlockFrame.draw(matrixPV, new Vector3f(firstBlock));
        Vector3i beforeBlock = Ray.beforeFirstBlock(player, server, 4);
        if (beforeBlock != null) BlockFrame.draw(matrixPV, new Vector3f(beforeBlock));

        Renderer.drawChunk(matrixPV, player.getCurrentChunk());

        gameUI.render();
        debug.render();
        pauseHandler.render();
    }

    public static Player getPlayer() {
        return getInstance().player;
    }

    public static Server getServer() {
        return getInstance().server;
    }


    public static Game joinGame(String addr, int port) {
        if (game == null) {
            try {
                game = new Game(new ServerConnection(addr, port));
            } catch (IOException e) {
                e.printStackTrace();
                game = null;
            }
        }
        return game;
    }

    public static Game openGame(String worldName) {
        if (game == null) {
            game = new Game(new LocalWorld());
        }
        return game;
    }

    public static Game makeGame(String worldName, long seed) {
        if (game == null) {
            game = new Game(new LocalWorld());
        }
        return game;
    }

    public static Game makeGame(String worldName, long seed, WorldGenerator generator) {
        if (game != null) throw new IllegalStateException();
        game = new Game(new LocalWorld());
        return game;
    }

    public static Game getInstance() {
        if (game == null) game = new Game();
        return game;
    }

    /**
     * force reload a specific chunk.
     * @param blockPos chunk position in block coordinates
     */
    public void setBlockUpdate(Vector3i blockPos) {
        renderer.updateBlock(blockPos);
    }

    @Override
    public void updateBlock(Vector3i block) {
        this.renderer.updateBlock(block);
    }

    @Override
    public void updateBlock(int x, int y, int z) {
        updateBlock(new Vector3i(x, y, z));
    }

    @Override
    public void loadChunk(int cX, int cY, int cZ) {

    }

    public void closeGame() throws IOException {
        server.close();
    }
}