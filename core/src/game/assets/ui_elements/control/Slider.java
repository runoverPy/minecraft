package game.assets.ui_elements.control;

import java.util.function.Function;

import game.assets.ui_elements.*;
import game.assets.ui_elements.asset.ColorBox;
import game.assets.ui_elements.asset.ImageBox;
import game.assets.ui_elements.asset.TextBox;
import game.main.Main;
import game.util.Image;
import game.util.relay.FloatRelay;
import game.util.relay.IntRelay;
import game.util.relay.ObjectRelay;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class Slider<T extends Number> extends ChildBox {
    private double sliderValue;
    private final String name;
    private final ObjectRelay<T> value;
    private final Function<Double, T> converter;
    private final Function<T, Double> invConverter;

    private final ColorBox scrollRail;
    private final ColorBox frame;
    private final Knob knob;

    public Slider(int width, int height, int xOffset, int yOffset, Component parent, String name, ObjectRelay<T> value, Function<Double, T> converter, Function<T, Double> invConverter) {
        super(width, height, xOffset, yOffset, parent);
        scrollRail = new ColorBox(width - 2, height - 2, 1, 1, this, new Vector4f(0.75f, 0.75f, 0.75f, 1f));
        frame = new ColorBox(getWidth(), getHeight(), 0, 0, this, new Vector4f(0.375f, 0.375f, 0.375f, 1f));

        knob = new Knob(8, height, 0, 0, this);

        this.name = name;
        this.value = value;
        this.invConverter = invConverter;
        this.converter = converter;

        double mappedValue = invConverter.apply(value.getValue());
        if (!(0 <= mappedValue && mappedValue <= 1)) {
            value.setValue(converter.apply(0.5d));
        }
        this.sliderValue = invConverter.apply(value.getValue());
    }

    public static Slider<Float> floatSlider(int width, int height, int xOffset, int yOffset, Component parent, String name, FloatRelay buffer) {
        return new Slider<>(width, height, xOffset, yOffset, parent, name, buffer, Double::floatValue, Float::doubleValue);
    }

    public static Slider<Float> scaleSlider(int width, int height, int xOffset, int yOffset, Component parent, String name, FloatRelay buffer, float start, float stop) {
        Function<Double, Float> converter = d -> (float) (d * (stop - start) + start);
        Function<Float, Double> invConverter = f -> (double) (f - start) / (stop - start);
        return new Slider<>(width, height, xOffset, yOffset, parent, name, buffer, converter, invConverter);
    }

    public static Slider<Integer> rangeSlider(int width, int height, int xOffset, int yOffset, Component parent, String name, IntRelay buffer, int start, int stop) {
        Function<Double, Integer> converter = d -> (int) Math.round(d * (stop - start)) + start;
        Function<Integer, Double> invConverter = i -> (double) (i - start) / (stop - start);
        return new Slider<>(width, height, xOffset, yOffset, parent, name, buffer, converter, invConverter);
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        TextBox description = new TextBox(getWidth() - 2, getHeight() - 2, 1, 1, this, this.name + ": " + value.getValue().toString(), true, true);

        frame.draw(pxScale, matrix4f);
        scrollRail.draw(pxScale, matrix4f);
        knob.draw(pxScale, matrix4f);
        description.draw(pxScale, matrix4f);
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        return knob.onMouseEvent(event, pxScale);
    }

    public double getValue() {
        return sliderValue;
    }

    private class Knob extends ChildBox {
        private boolean clicked;
        private int clickOffset;

        public Knob(int width, int height, int xOffset, int yOffset, Component parent) {
            super(width, height, xOffset, yOffset, parent);
            clicked = false;
            clickOffset = 0;
        }

        @Override
        public int getCornerX(int pxScale) {
            return (int) (getValue() * (getParent().getWidth(pxScale) - getWidth(pxScale))) + super.getCornerX(pxScale);
        }

        @Override
        public void draw(int pxScale, Matrix4f matrix4f) {
            if (Main.getActiveWindow().getMouseButton(GLFW_MOUSE_BUTTON_LEFT) != GLFW_PRESS && clicked) {
                clicked = false;
            }

            if (clicked) {
                int
                  mouseX = Main.getActiveWindow().getCursorPos().x().intValue(),
                  knobPos = mouseX - clickOffset,
                  scrollLength = getParent().getWidth(pxScale) - getWidth(pxScale),
                  leftEnd = getParent().getCornerX(pxScale),
                  rightEnd = leftEnd + scrollLength,
                  framedLength = Math.max(leftEnd, Math.min(knobPos, rightEnd)) - leftEnd;

                value.setValue(converter.apply((double) framedLength / scrollLength));
                sliderValue = invConverter.apply(value.getValue());
            }

            Vector4f frameColor = clicked || hovering(pxScale) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);

            ColorBox outerBox = new ColorBox(getWidth(), getHeight(), 0, 0, this, frameColor);
            ImageBox center = new ImageBox(getWidth() - 2, getHeight() - 2, 1, 1, this, Image.loadImage("/img/stone.png"));
            outerBox.draw(pxScale, matrix4f);
            center.draw(pxScale, matrix4f);
        }

        @Override
        public boolean onMouseEvent(MouseEvent event, int pxScale) {
            if (event.eventType == MouseEvent.EventType.PRESSED && contains(event, pxScale)) {
                clicked = true;
                clickOffset = (int) event.mouseX - getCornerX(pxScale);
                return true;
            }
            return false;
        }
    }

    @Override
    public Component getTopComponent(int x, int y, int pxScale) {
        if (knob.contains(x, y, pxScale)) return knob;
        return super.getTopComponent(x, y, pxScale);
    }
}
