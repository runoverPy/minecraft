package game.mechanics.collision;

import org.joml.Vector3f;

import java.util.Iterator;

public class Corner {
    private static final Vector3f[] corners;

    private Corner() {}

    static {
        corners = new Vector3f[8];
        for (int i = 0; i < 8; i++) {
            corners[i] = new Vector3f(
                    (-0.5f + i % 2),
                    (-0.5f + (float) (i / 2) % 2),
                    (float) (i / 4)
            );
        }
    }

    public static Corner[] getForDirection(Direction direction) {

        return null;
    }
}
