package game.mechanics.collision;

import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.EnumMap;
import java.util.Map;

public class Hitbox {
    private final float width, depth, height; // x, y, z respectively

    public Hitbox(float width, float depth, float height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

    /**
     * returns an array of the corners of the hitbox.
     * @param hitboxPos the current position of the hitbox, located at the center of the base of the box.
     * @return An array containing the absolute vectors for the corners of the hitbox
     */
    public Vector3f[] getCorners(Vector3f hitboxPos) {
        Vector3f[] out = new Vector3f[8];
        for (int i = 0; i < 8; i++) {
            out[i] = new Vector3f(
                    width * (-0.5f + i % 2),
                    depth * (-0.5f + (float) (i / 2) % 2),
                    height * (float) (i / 4)
            ).add(hitboxPos);
        }
        return out;
    }

    public Map<Direction, Boolean> getCollisions(Collidable other) {
        return null;
    }
}
