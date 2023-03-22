package game.assets.ui_elements;

public class MouseEvent {
    public final EventType eventType;
    public final double mouseX, mouseY;

    public MouseEvent(EventType eventType, double mouseX, double mouseY) {
        this.eventType = eventType;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public enum EventType {
        PRESSED,
        RELEASED
    }
}
