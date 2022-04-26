package game.assets.widgets;

import game.assets.boxes.Box;
import game.util.buffer.BoolBuffer;
import game.util.buffer.FloatBuffer;
import game.util.buffer.IntBuffer;

public interface Widget {
    ChildBox create(int width, int height, int xOffset, int yOffset, Box parent);

    static Widget button(String name, Runnable callback) {
        return (width, height, xOffset, yOffset, parent) -> new Button(width, height, xOffset, yOffset, parent, name, callback);
    }

    static Widget query(StringBuffer buffer) {
        return (width, height, xOffset, yOffset, parent) -> new Query(width, height, xOffset, yOffset, parent, buffer);
    }

    static Widget slider(String name, FloatBuffer buffer) {
        return (width, height, xOffset, yOffset, parent) -> Slider.floatSlider(width, height, xOffset, yOffset, parent, name, buffer);
    }

    static Widget slider(String name, FloatBuffer buffer, float start, float stop) {
        return (width, height, xOffset, yOffset, parent) -> Slider.scaleSlider(width, height, xOffset, yOffset, parent, name, buffer, start, stop);
    }

    static Widget slider(String name, IntBuffer buffer, int start, int stop) {
        return (width, height, xOffset, yOffset, parent) -> Slider.rangeSlider(width, height, xOffset, yOffset, parent, name, buffer, start, stop);
    }

    static Widget switchButton(String name, BoolBuffer buffer) {
        return (width, height, xOffset, yOffset, parent) -> new SwitchButton(width, height, xOffset, yOffset, parent, name, buffer);
    }

    static Widget textBox(String text, boolean centered, boolean shaded) {
        return (width, height, xOffset, yOffset, parent) -> new TextBox(width, height, xOffset, yOffset, parent, text, centered, shaded);
    }
}
