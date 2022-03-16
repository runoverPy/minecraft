package game.mechanics.rendering;

import org.joml.Matrix4f;

public interface Tile {
    void render(Matrix4f matrixPV);
}
