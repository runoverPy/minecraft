package game.assets.event;

public class ScrollEvent extends Event {
    public static final EventType<ScrollEvent> SCROLL =
            new EventType<>(ANY, "SCROLL");

    private double deltaX, deltaY;

    public ScrollEvent(double deltaX, double deltaY) {
        super(SCROLL);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }
}
