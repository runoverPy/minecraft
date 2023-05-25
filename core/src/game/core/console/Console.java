package game.core.console;

import game.assets.overlays.Overlay;
import org.joml.Matrix4f;

import java.util.List;

public class Console extends Overlay {
    private List<String> messages;
    private StringBuffer buffer;



    public void render() {
        Matrix4f matrix = make2DMatrix();
    }
}
