package game.assets.overlays;

import game.assets.Toggle;
import game.assets.mcui.Align;
import game.assets.mcui.ContentRoot;
import game.assets.mcui.atoms.TextTile;
import game.assets.mcui.container.AnchorPane;
import game.assets.mcui.container.StackContainer;
import game.core.rendering.ChunkRenderer;
import game.core.rendering.Renderer;
import game.core.server.Server;
import game.main.Main;
import game.mechanics.entities.Player;
import game.util.Ray;
import game.window.GLFWWindow;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_F3;
import static org.lwjgl.opengl.GL46.*;

public class Debug extends ContentRoot {
    private long lastTime;
    private final Server server;
    private final Player player;
    private final Renderer renderer;
    private final FPSCounter counter;
    private static final int toggleKey = GLFW_KEY_F3;
    private final Toggle visible;

    private TextTile leftText, rightText;

    public Debug(Server server, Player player, Renderer renderer) {
        this.server = server;
        this.player = player;
        this.renderer = renderer;
        this.counter = new FPSCounter();
        lastTime = System.currentTimeMillis();
        visible = new Toggle(toggleKey);
        leftText = new TextTile();
        rightText = new TextTile();
        StackContainer.setAlignment(leftText, Align.TOP_LEFT);
        leftText.setAlign(Align.TOP_LEFT);
        StackContainer.setAlignment(rightText, Align.TOP_RIGHT);
        rightText.setAlign(Align.TOP_RIGHT);
        StackContainer innerContainer = new StackContainer();
        innerContainer.getChildren()
          .addAll(leftText, rightText);
        AnchorPane.setTopAnchor(innerContainer, 4.0);
        AnchorPane.setBottomAnchor(innerContainer, 4.0);
        AnchorPane.setLeftAnchor(innerContainer, 4.0);
        AnchorPane.setRightAnchor(innerContainer, 4.0);
        AnchorPane outerContainer = new AnchorPane();
        GLFWWindow.Dimension windowSize = Main.getActiveWindow().getWindowSize();
        outerContainer.setSize(windowSize.width(), windowSize.height());
        outerContainer.getChildren()
          .add(innerContainer);
        setRoot(outerContainer);

        Main.getActiveWindow().addWindowSizeCallback(outerContainer::setSize);
    }

    public void render() {
        glDisable(GL_DEPTH_TEST);
        Matrix4f matrixPV = Overlay.make2DMatrix();
        render(matrixPV);
        glEnable(GL_DEPTH_TEST);
    }

    @Override
    public void render(Matrix4f matrix) {
        long newTime = System.currentTimeMillis();
        long deltaTime = newTime - lastTime;
        lastTime = newTime;
        counter.addFrame(deltaTime);
        counter.clean();

        visible.check();
        if (!isVisible()) return;

        Vector3f camPos = player.getCamPos();
        Vector3f camVel = player.getVel();
        Vector3i chunk = player.getCurrentChunk();
        Vector3i firstBlock = Ray.findFirstBlock(player, server, 10);
        double vertical = player.getVertical(), horizontal = player.getHorizontal();

        leftText.edit()
          .reset()
          .printf("XYZ: %.3f; %.3f; %.3f\n", camPos.x(), camPos.y(), camPos.z())
          .printf("Vel: %.3f; %.3f; %.3f\n", camVel.x(), camVel.y(), camVel.z())
          .printf("Angle: %.3f; %.3f\n", vertical * 360 / (Math.PI * 2) % 360, horizontal * 360 / (Math.PI * 2) % 360)
          .printf("Chunk: %d, %d, %d\n", chunk.x(), chunk.y(), chunk.z());

        rightText.edit()
          .reset()
          .println("FPS: " + (long) counter.getAvgFrameRate());
        if (renderer instanceof ChunkRenderer chunkRenderer) {
            rightText.edit()
              .printf("Loaded Chunks W: %d, R: %d\n", server.getChunkCount(), chunkRenderer.getChunkCount())
              .printf("executor queue: %d, tasks: %d, threads: %d\n",
                chunkRenderer.getExecutorQueueSize(), chunkRenderer.getExecutorTasks(), chunkRenderer.getExecutorThreads());
        }

        if (firstBlock != null) {
            rightText.edit()
              .printf("Looking at block: %d, %d, %d\n", firstBlock.x(), firstBlock.y(), firstBlock.z());
        }

        super.render(matrix);
    }

    public boolean isVisible() {
        return visible.isToggled();
    }
}
