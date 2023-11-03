package game.assets.menus;

import game.assets.event.EventGenerator;
import game.main.Main;

import java.util.Deque;
import java.util.LinkedList;

public class MenuHandler {
    private EventGenerator generator;
    protected final Deque<Menu> menus;
    private boolean disabled;
    private final int number;
    private static int count;

    protected MenuHandler() {
        this(false);
    }

    protected MenuHandler(boolean disabled) {
        this.generator = new EventGenerator();
        if (!disabled) {
            generator.attachCallbacks(Main.getActiveWindow());
        }
        this.disabled = disabled;
        this.number = count++;
        this.menus = new LinkedList<>();
    }

    public void prev() {
        Menu past = menus.pop();
        past.detach();
        if (!menus.isEmpty()) {
            menus.peek().attach();
            generator.setTarget(menus.peek());
        }
    }

    public void next(Menu next) {
        if (next == null) throw new NullPointerException();
        if (!menus.isEmpty()) {
            menus.peek().detach();
        }
        next.attach();
        generator.setTarget(next);
        menus.push(next);
    }

    public void render() {
        if (!menus.isEmpty()) menus.peek().render();
    }

    public void enable() {
        if (!disabled) return;
        generator.attachCallbacks(Main.getActiveWindow());
        disabled = false;
    }

    public void disable() {
        if (disabled) return;
        generator.detachCallbacks(Main.getActiveWindow());
        disabled = true;
    }

    public void cleanup() {
        generator.detachCallbacks(Main.getActiveWindow());
        if (!menus.isEmpty())
            menus.peek().detach();
        menus.clear();
    }

    public boolean isEnabled() {
        return !disabled;
    }

    public static MenuHandler mainMenu() {
        MenuHandler out = new MenuHandler();
        out.next(new MainMenu(out));
        return out;
    }

    public static MenuHandler mainMenu(Throwable error) {
        MenuHandler out = mainMenu();
        out.addError(error);
        return out;
    }

    public void addError(Throwable error) {
        this.next(new ErrorScreen(this, error));
    }

    @Override
    protected final void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalizing " + this + " #" + number);
    }
}