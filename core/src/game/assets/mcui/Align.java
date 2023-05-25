package game.assets.mcui;

public enum Align {
    TOP_LEFT(AlignV.TOP, AlignH.LEFT),
    TOP_CENTER(AlignV.TOP, AlignH.CENTER),
    TOP_RIGHT(AlignV.TOP, AlignH.RIGHT),
    CENTER_LEFT(AlignV.CENTER, AlignH.LEFT),
    CENTER(AlignV.CENTER, AlignH.CENTER),
    CENTER_RIGHT(AlignV.CENTER, AlignH.RIGHT),
    BOTTOM_LEFT(AlignV.BOTTOM, AlignH.LEFT),
    BOTTOM_CENTER(AlignV.BOTTOM, AlignH.CENTER),
    BOTTOM_RIGHT(AlignV.BOTTOM, AlignH.RIGHT);

    final AlignH horizontal;
    final AlignV vertical;

    Align(AlignV vertical, AlignH horizontal) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public AlignH getHorizontal() {
        return horizontal;
    }

    public AlignV getVertical() {
        return vertical;
    }

    public int getXOffset(int outerWidth, int innerWidth) {
        return horizontal.getXOffset(outerWidth, innerWidth);
    }

    public int getYOffset(int outerHeight, int innerHeight) {
        return vertical.getYOffset(outerHeight, innerHeight);
    }
}
