package game.assets.overlays;

import game.assets.Reticle;
import game.main.Main;
import game.mechanics.inventory.Hotbar;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;

public class GameUI extends Overlay {
    private boolean inInventory;
    private final Hotbar hotbar;

    public GameUI() {
        inInventory = false;
        this.hotbar = new Hotbar();
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

    @Override
    public void render() {
        Matrix4f matrixPV = make2DMatrix();

        new Reticle().drawReticle(matrixPV);
    }
}
