package game.main;

import game.assets.mcui.event.MouseEvent;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(MouseEvent.MOUSE_PRESSED.getTypeHierarchy()));
    }
}
