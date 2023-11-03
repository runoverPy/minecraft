package game.assets.overlays;

import game.assets.mcui.export.Crosshair;
import game.assets.mcui.ContentRoot;
import game.main.Main;
import game.mechanics.entities.Player;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;

public class GameUI extends ContentRoot {
    private boolean inInventory;
    private final Player player;

    public GameUI(Player player) {
        this.player = player;
    }

    public void openInventory() {
        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        inInventory = true;
    }

    public void closeInventory() {
        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        inInventory = false;
    }

    public boolean isInInventory() {
        return inInventory;
    }

    public void render() {
        Matrix4f matrix = Overlay.make2DMatrix();

        double
          horizontal = player.getHorizontal(),
          vertical = player.getVertical();

        new Crosshair().drawCompass(matrix, horizontal, vertical);
        player.getHotbar().draw(matrix);
    }
}
