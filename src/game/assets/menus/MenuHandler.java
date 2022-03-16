package game.assets.menus;

import game.assets.Scene;

import java.util.Deque;
import java.util.LinkedList;

public class MenuHandler extends Scene {
    private final Deque<Menu> menus;

    protected MenuHandler() {
        this.menus = new LinkedList<>();
    }

    public void prev() {
        menus.pop();
    }

    public void next(Menu next) {
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
}