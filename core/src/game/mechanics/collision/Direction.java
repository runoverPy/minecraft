package game.mechanics.collision;

public enum Direction {
    NORTH (1, 0, 0),
    SOUTH (-1, 0, 0),
    WEST (0, 1, 0),
    EAST (0, -1, 0),
    UP (0, 0, 1),
    DOWN (0, 0, -1),
    FILL (0, 0, 0);

    private final float x, y, z;

    Direction(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Direction get(int component, float direction) {
        if (direction == 0 || component < 0 || component > 2) return null;
        switch (component) {
            case 0: {
                if (direction > 0) return NORTH;
                else return SOUTH;
            } case 1: {
                if (direction > 0) return WEST;
                else return EAST;
            } case 2: {
                if (direction > 0) return UP;
                else return DOWN;
            } default: return null;
        }
    }

    public Corner[] getCorners() {
        return null;
    }
}
