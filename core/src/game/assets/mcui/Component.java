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
    private int layoutX, layoutY;
    private int width, height;
    private boolean resizeable = true;
    private Component parent; // TODO: 23.05.23 currently unused, finish implementing
    private final ComponentEventDispatcher dispatcher = new ComponentEventDispatcher();

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        if (!isResizeable()) return;
        if (width < minWidth() || maxWidth() < width) throw new IllegalArgumentException("Illegal width value");
        this.width = width;
        layout();
    }

    public void setHeight(int height) {
//        setSize(getWidth(), height);
        if (!isResizeable()) return;
        if (height < minHeight() || maxHeight() < height) throw new IllegalArgumentException("Illegal height value");
        this.height = height;
        layout();
    }

    public void setSize(int width, int height) {
        if (!isResizeable()) return;
        if (width < minWidth() || maxWidth() < width) throw new IllegalArgumentException("Illegal width value");
        if (height < minHeight() || maxHeight() < height) throw new IllegalArgumentException("Illegal height value");
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

    public abstract void render(Matrix4f matrix);

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
}
