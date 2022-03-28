package game.assets.overlays;

import game.assets.Callback;
import game.assets.text.Advance;
import game.assets.text.Align;
import game.assets.text.TextField;
import game.main.Main;
import game.mechanics.entities.Player;
import game.util.Ray;
import game.core.server.core.Server;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public class Debug extends Overlay {
    private boolean isShown;
    private long lastTime;
    private final Server server;
    private final Player player;
    private static final int toggleKey = GLFW_KEY_F3;
    private final Callback toggle;

    public Debug(Server server, Player player) {
        this.server = server;
        this.player = player;
        isShown = false;
        lastTime = System.currentTimeMillis();
        toggle = new Callback(toggleKey, () -> {
            isShown = !isShown;
        });
    }

    @Override
    public void render() {
        toggle.check();

        if (!isShown) return;

        glDisable(GL_DEPTH_TEST);
        Matrix4f matrixPV = make2DMatrix();

        float aspectRatio = Main.getActiveWindow().getAspectRatio();

        long newTime = System.currentTimeMillis();
        long deltaTime = newTime - lastTime;
        lastTime = newTime;
        
        TextField leftTextField = new TextField(matrixPV, 0.1f, new Vector3f(-aspectRatio, 1, 0), Align.TOP, Advance.DOWN_RIGHT, new Vector4f(0f, 0f, 0f, 1f));
        TextField rightTextField = new TextField(matrixPV, 0.1f, new Vector3f(aspectRatio, 1, 0), Align.TOP, Advance.DOWN_LEFT, new Vector4f(1f, 0f, 1f, 1f));

        Vector3f camPos = player.getCamPos();
        Vector3f camVel = player.getVeloctiy();
        Vector3i chunk = player.getCurrentChunk();
        Vector3i firstBlock = Ray.findFirstBlock(player, server, 10);
        double vertical = player.getVertical(), horizontal = player.getHorizontal();

        leftTextField.print("");
        leftTextField.print(String.format(" XYZ: %.3f; %.3f; %.3f", camPos.x(), camPos.y(), camPos.z()));
        leftTextField.print(String.format(" Vel: %.3f; %.3f; %.3f", camVel.x(), camVel.y(), camVel.z()));
        leftTextField.print(String.format(" Angle: %.3f; %.3f", vertical * 360 / (Math.PI * 2) % 360, horizontal * 360 / (Math.PI * 2) % 360));
        leftTextField.print(String.format(" Chunk: %d, %d, %d", chunk.x(), chunk.y(), chunk.z()));

        rightTextField.print("FPS: " + (long) (1 / (deltaTime / 1000f)));
        if (firstBlock != null) {
            rightTextField.flush();
            rightTextField.print(String.format("Looking at block: %d, %d, %d", firstBlock.x(), firstBlock.y(), firstBlock.z()));
        }

        glEnable(GL_DEPTH_TEST);
    }
}
