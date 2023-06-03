package game.assets.menus;

public interface Menu {
    void render();
    default void attach() {}
    default void detach() {}
}
