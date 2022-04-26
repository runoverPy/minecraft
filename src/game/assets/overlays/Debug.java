package game.assets.overlays;

import game.assets.Callback;
import game.assets.text.Advance;
import game.assets.text.TextField;
import game.main.Main;
import game.mechanics.entities.Player;
import game.util.Ray;
import game.core.server.Server;
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
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();

        toggle.check();

        if (!isShown) return;

        glDisable(GL_DEPTH_TEST);
        Matrix4f matrixPV = make2DMatrix();

        long newTime = System.currentTimeMillis();
        long deltaTime = newTime - lastTime;
        lastTime = newTime;
        
        TextField leftTextField = new TextField(true, 16, 16, Advance.DOWN_RIGHT, new Vector4f(0f, 0f, 0f, 1f));
        TextField rightTextField = new TextField(false, winWidth - 16, 16, Advance.DOWN_LEFT, new Vector4f(0f, 0f, 0f, 1f));

        Vector3f camPos = player.getCamPos();
        Vector3f camVel = player.getVel();
        Vector3i chunk = player.getCurrentChunk();
        Vector3i firstBlock = Ray.findFirstBlock(player, server, 10);
        double vertical = player.getVertical(), horizontal = player.getHorizontal();

//        leftTextField.println("");
        leftTextField.printlnf("XYZ: %.3f; %.3f; %.3f", camPos.x(), camPos.y(), camPos.z());
        leftTextField.printlnf("Vel: %.3f; %.3f; %.3f", camVel.x(), camVel.y(), camVel.z());
        leftTextField.printlnf("Angle: %.3f; %.3f", vertical * 360 / (Math.PI * 2) % 360, horizontal * 360 / (Math.PI * 2) % 360);
        leftTextField.printlnf("Chunk: %d, %d, %d", chunk.x(), chunk.y(), chunk.z());

        rightTextField.println("FPS: " + (long) (1 / (deltaTime / 1000f)));

        if (firstBlock != null) {
            rightTextField.printlnf("Looking at block: %d, %d, %d", firstBlock.x(), firstBlock.y(), firstBlock.z());
        }

        leftTextField.draw(4, matrixPV);
        rightTextField.draw(4, matrixPV);

        glEnable(GL_DEPTH_TEST);
    }

    public boolean isVisible() {
        return isShown;
    }
}
