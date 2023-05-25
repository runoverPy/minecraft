package game.assets.mcui;

public enum AlignH {
    LEFT, CENTER, RIGHT;

    public int getXOffset(int outerWidth, int innerWidth) {
        return switch (this) {
            case LEFT -> 0;
            case CENTER -> (outerWidth - innerWidth) / 2;
            case RIGHT -> outerWidth - innerWidth;
        };
    }
}
