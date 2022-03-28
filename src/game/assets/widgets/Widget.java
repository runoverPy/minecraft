package game.assets.widgets;

import game.util.Fn;
import game.util.buffer.BoolBuffer;
import game.util.buffer.FloatBuffer;

public interface Widget {
    ChildBox create(int width, int height, int xOffset, int yOffset, Box parent);

    static Widget button(String name, Fn callback) {
        return (width, height, xOffset, yOffset, parent) -> new Button(width, height, xOffset, yOffset, parent, name, callback);
    }

    static Widget query(StringBuffer buffer) {
        return (width, height, xOffset, yOffset, parent) -> new Query(width, height, xOffset, yOffset, parent, buffer);
    }

    static Widget slider(FloatBuffer buffer) {
        return (width, height, xOffset, yOffset, parent) -> new Slider(width, height, xOffset, yOffset, parent, buffer);
    }

    static Widget switchButton(String name, BoolBuffer buffer) {
        return (width, height, xOffset, yOffset, parent) -> new SwitchButton(width, height, xOffset, yOffset, parent, name, buffer);
    }

    static Widget propTextBox(String text, boolean centered, boolean shaded) {
        return (width, height, xOffset, yOffset, parent) -> new PropTextBox(width, height, xOffset, yOffset, parent, text, centered, shaded);
    }
}
