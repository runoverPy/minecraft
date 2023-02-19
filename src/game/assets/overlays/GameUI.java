package game.assets.overlays;

import game.assets.Crosshair;
import game.assets.ui_elements.Hotbar;
import org.joml.Matrix4f;

public class GameUI {
//    private boolean inInventory;
    private final Hotbar hotbar;

    public GameUI(Hotbar hotbar) {
//        inInventory = false;
        this.hotbar = hotbar;
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
        hotbar.draw(matrixPV);
    }
}
