package game.assets.mcui;

import game.assets.event.Event;
import game.assets.event.EventProcessor;
import game.assets.event.EventType;
import game.assets.event.dispatch.ComponentEventDispatcher;
import game.assets.event.dispatch.EventDispatchChain;
import game.assets.event.EventTarget;
import game.assets.event.dispatch.EventDispatcher;
import game.assets.event.dispatch.EventLauncher;
import game.main.Main;
import game.window.GLFWWindow;
import org.joml.Matrix4f;

public abstract class Component implements EventTarget {
    protected static ItemScale scale = ItemScale.LARGE;
    private int layoutX, layoutY;
    private int width, height;
    private boolean resizeable = true;
    private Component parent;
    private ContentRoot root;
    private final ComponentEventDispatcher dispatcher = new ComponentEventDispatcher();
    private int lastPxScale = 0;

    public Component() {
        width = minWidth();
        height = minHeight();
    }

    public int getLayoutX() {
        return layoutX;
    }

    public final int getGlobalX() {
        Component parent = getParent();
        if (parent == null)
            return getLayoutX();
        else
            return parent.getGlobalX() + getLayoutX();
    }

    public int getLayoutY() {
        return layoutY;
    }

    public final int getGlobalY() {
        Component parent = getParent();
        if (parent == null)
            return getLayoutY();
        else
            return parent.getGlobalY() + getLayoutY();
    }

    public final void setLayoutX(int layoutX) {
        this.layoutX = layoutX;
    }

    public final void setLayoutY(int layoutY) {
        this.layoutY = layoutY;
    }

    public final void setLayoutPos(int layoutX, int layoutY) {
        setLayoutX(layoutX);
        setLayoutY(layoutY);
    }

    public void layoutIfScaleChanged() {
        int nextPxScale = Component.getPxScale();
        if (lastPxScale != nextPxScale) {
            lastPxScale = nextPxScale;
            layout();
        }
    }

    public int getWidth() {
        return width >= 0 ? width : calcWidth();
    }

    public int calcWidth() {
        return 0;
    }

    public int getHeight() {
        return height >= 0 ? height : calcHeight();
    }

    public int calcHeight() {
        return 0;
    }

    public void setWidth(int width) {
        if (!isResizeable()) return;
        this.width = width;
        layout();
    }

    public void setHeight(int height) {
        if (!isResizeable()) return;
        this.height = height;
        layout();
    }

    public void setSize(int width, int height) {
        if (!isResizeable()) return;
        this.width = width;
        this.height = height;
        layout();
    }

    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
    }

    public boolean isResizeable() {
        return resizeable;
    }

    public static void setScale(ItemScale scale) {
        Component.scale = scale;
    }

    public static ItemScale getScale() {
        return Component.scale;
    }

    public static int getPxScale() {
        int winWidth, winHeight;
        winWidth = Main.getActiveWindow().getWidth();
        winHeight = Main.getActiveWindow().getHeight();

        int pxWidth, pxHeight;
        pxWidth = winWidth / scale.getPixels();
        pxHeight = winHeight / scale.getPixels();
        return Math.min(pxWidth, pxHeight);
    }

    public void render(Matrix4f matrix) {

    }

    public void layout() {}

    protected int minWidth() {
        return 0;
    }

    protected int maxWidth() {
        return Integer.MAX_VALUE;
    }

    protected int minHeight() {
        return 0;
    }

    protected int maxHeight() {
        return Integer.MAX_VALUE;
    }

    public final boolean contains(double x, double y) {
        int
          top = getGlobalY(),
          left = getGlobalX(),
          right = left + getWidth(),
          bottom = top + getHeight();

        return left < x && x < right && top < y && y < bottom;
    }

    public final boolean isHovering() {
        GLFWWindow.Position<Double> cursorPos = Main.getActiveWindow().getCursorPos();
        return contains(cursorPos.x(), cursorPos.y());
    }

    public Component pick(double x, double y) {
        return contains(x, y) ? this : null;
    }

    public Component getParent() {
        return this.parent;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public ContentRoot getRoot() {
        return root;
    }

    public void setRoot(ContentRoot root) {
        this.root = root;
    }

    @Override
    public final EventDispatchChain buildEventDispatchChain(EventDispatchChain chain) {
        for (Component component = this; component != null; component = component.getParent()) {
            chain = chain.prepend(component.getDispatcher());
        }
        return chain;
    }

    public EventDispatcher getDispatcher() {
        return getDispatcherInternal();
    }

    private ComponentEventDispatcher getDispatcherInternal() {
        return dispatcher;
    }

    public <T extends Event> void addEventHandler(EventType<T> eventType, EventProcessor<T> eventProcessor) {
        getDispatcherInternal().getProcessorEventDispatcher().addEventHandler(eventType, eventProcessor);
    }

    public <T extends Event> void removeEventHandler(EventType<T> eventType, EventProcessor<T> eventProcessor) {
        getDispatcherInternal().getProcessorEventDispatcher().removeEventHandler(eventType, eventProcessor);
    }

    @Override
    public final Event fireEvent(Event event) {
        return EventLauncher.fireEvent(this, event);
    }

    public enum ItemScale {
        SMALL (512),
        MEDIUM (384),
        LARGE (256),
        GIANT (128);

        private final int pixels;

        ItemScale(int pixels) {
            this.pixels = pixels;
        }

        public int getPixels() {
            return pixels;
        }
    }

    public void requestFocus() {
        if (root != null)
            root.requestFocus(this);
    } //todo implement

    public boolean hasFocus() {
        return root != null && root.getFocusedElement() == this;
    }

    public void focusAttached() {}

    public void focusDetached() {}
}
