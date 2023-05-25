package game.assets.mcui;

public enum AlignV {
    TOP, CENTER, BOTTOM;

    public int getYOffset(int outerHeight, int innerHeight) {
        return switch (this) {
            case TOP -> 0;
            case CENTER -> (outerHeight - innerHeight) / 2;
            case BOTTOM -> outerHeight - innerHeight;
        };
    }
}
