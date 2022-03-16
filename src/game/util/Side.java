package game.util;

import org.joml.Vector3f;
import org.joml.Vector3i;

public enum Side {
    TOP     (0, 0, 1),
    BOTTOM  (0, 0, -1),
    WEST    (0, 1, 0),
    EAST    (0, -1, 0),
    NORTH   (1, 0, 0),
    SOUTH   (-1, 0, 0);

    private final int x, y, z;

    Side(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i getVec() {
        return new Vector3i(this.x, this.y, this.z);
    }

    public Vector3f getVecF() {
        return new Vector3f(x, y, z);
    }

    public Side invert() {
        switch (this) {
            case TOP : return BOTTOM;
            case BOTTOM : return TOP;
            case WEST : return EAST;
            case EAST : return WEST;
            case NORTH : return SOUTH;
            case SOUTH : return NORTH;
            default: return null;
        }
    }
}

