package game.assets.ui_elements;

import game.util.Cyclic;
import game.util.relay.BoolRelay;
import game.util.relay.EnumRelay;
import game.util.relay.FloatRelay;
import game.util.relay.IntRelay;

public interface Widget {
    ChildBox create(int width, int height, int xOffset, int yOffset, Component parent);

    static Widget button(String name, Runnable callback) {
        return (width, height, xOffset, yOffset, parent) -> new Button(width, height, xOffset, yOffset, parent, name, callback);
    }

    static Widget button(String name, Runnable callback, BoolRelay isActive) {
        return (width, height, xOffset, yOffset, parent) -> new Button(width, height, xOffset, yOffset, parent, name, callback, isActive);
    }

    static Widget button(String name, BoolRelay buffer) {
        return (width, height, xOffset, yOffset, parent) -> new SwitchButton(width, height, xOffset, yOffset, parent, name, buffer);
    }

    static <E extends Enum<E> & Cyclic<E>> Widget button(String name, EnumRelay<E> buffer) {
        return (width, height, xOffset, yOffset, parent) -> new ToggleButton<>(width, height, xOffset, yOffset, parent, name, buffer);
    }

    static Widget query(StringBuffer buffer) {
        return (width, height, xOffset, yOffset, parent) -> new Query(width, height, xOffset, yOffset, parent, buffer);
    }

    static Widget slider(String name, FloatRelay buffer) {
        return (width, height, xOffset, yOffset, parent) -> Slider.floatSlider(width, height, xOffset, yOffset, parent, name, buffer);
    }

    static Widget slider(String name, FloatRelay buffer, float start, float stop) {
        return (width, height, xOffset, yOffset, parent) -> Slider.scaleSlider(width, height, xOffset, yOffset, parent, name, buffer, start, stop);
    }

    static Widget slider(String name, IntRelay buffer, int start, int stop) {
        return (width, height, xOffset, yOffset, parent) -> Slider.rangeSlider(width, height, xOffset, yOffset, parent, name, buffer, start, stop);
    }

    static Widget textBox(String text, boolean centered, boolean shaded) {
        return (width, height, xOffset, yOffset, parent) -> new TextBox(width, height, xOffset, yOffset, parent, text, centered, shaded);
    }
}
