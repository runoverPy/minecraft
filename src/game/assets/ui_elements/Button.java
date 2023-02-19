package game.assets.ui_elements;

import game.util.relay.BoolRelay;

public class Button extends AbstractButton {
    private final Runnable callback;

    /**
     * creates a button centered on a given point
     * width and height will be determined dynamically
     *
     * @param name the text to be displayed on the button
     * @param callback the function to be executed on click
     */

    public Button(int width, int height, int xOffset, int yOffset, Component parent, String name, Runnable callback) {
        this(width, height, xOffset, yOffset, parent, name, callback, new BoolRelay(true));
    }

    public Button(int width, int height, int xOffset, int yOffset, Component parent, String name, Runnable callback, BoolRelay buffer) {
        super(width, height, xOffset, yOffset, parent, name, buffer);
        this.callback = callback;
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        if (event.eventType == MouseEvent.EventType.PRESSED && contains(event, pxScale) && isActive()) {
            callback.run();
            return true;
        }
        return false;
    }
}