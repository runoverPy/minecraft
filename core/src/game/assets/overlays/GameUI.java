package game.assets.overlays;

import game.assets.Crosshair;
import game.mechanics.entities.Player;
import org.joml.Matrix4f;

public class GameUI {
//    private boolean inInventory;
    private final Player player;

    public GameUI(Player player) {
        this.player = player;
    }

//    public void openInventory() {
//        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_NORMAL);
//        inInventory = true;
//    }
//
//    public void closeInventory() {
//        Main.getActiveWindow().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);
//        inInventory = false;
//    }
//
//    public boolean isInInventory() {
//        return inInventory;
//    }

    public void render() {
        Matrix4f matrixPV = Overlay.make2DMatrix();

        new Crosshair().draw(matrixPV);
        player.getHotbar().draw(matrixPV);
    }
}
