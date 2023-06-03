package game.assets.menus;

import java.util.Deque;
import java.util.LinkedList;

public class MenuHandler {
    private final Deque<Menu> menus;

    protected MenuHandler() {
        this.menus = new LinkedList<>();
    }

    public void prev() {
        menus.pop().detach();
        if (!menus.isEmpty())
            menus.peek().attach();
    }

    public void next(Menu next) {
        if (!menus.isEmpty())
            menus.peek().detach();
        next.attach();
        menus.push(next);
    }

    public void render() {
        if (!menus.isEmpty()) menus.peek().render();
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
}