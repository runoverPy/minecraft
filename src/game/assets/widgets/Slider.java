package game.assets.widgets;

import java.util.function.Function;

import game.assets.boxes.Box;
import game.main.Main;
import game.util.Image;
import game.util.buffer.FloatBuffer;
import game.util.buffer.IntBuffer;
import game.util.buffer.NumericBuffer;
import org.joml.Matrix4f;
import org.joml.Vector4f;

class Slider<T extends Number> extends ChildBox {
    private double sliderValue;
    private final String name;
    private final NumericBuffer<T> value;
    private final Function<Double, T> converter;
    private final Function<T, Double> invConverter;

    private final ChildBox scrollRail;
    private final Knob knob;

    public Slider(int width, int height, int xOffset, int yOffset, Box parent, String name, NumericBuffer<T> value, Function<Double, T> converter, Function<T, Double> invConverter) {
        super(width, height, xOffset, yOffset, parent);
        scrollRail = new ColorBox(width - 2, height - 2, 1, 1, this, new Vector4f(0.75f, 0.75f, 0.75f, 1f));
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

    static Slider<Float> floatSlider(int width, int height, int xOffset, int yOffset, Box parent, String name, FloatBuffer buffer) {
        return new Slider<>(width, height, xOffset, yOffset, parent, name, buffer, Double::floatValue, Float::doubleValue);
    }

    static Slider<Float> scaleSlider(int width, int height, int xOffset, int yOffset, Box parent, String name, FloatBuffer buffer, float start, float stop) {
        Function<Double, Float> converter = d -> (float) (d * (stop - start) + start);
        Function<Float, Double> invConverter = f -> (double) (f - start) / (stop - start);
        return new Slider<>(width, height, xOffset, yOffset, parent, name, buffer, converter, invConverter);
    }

    static Slider<Integer> rangeSlider(int width, int height, int xOffset, int yOffset, Box parent, String name, IntBuffer buffer, int start, int stop) {
        Function<Double, Integer> converter = d -> (int) Math.round(d * (stop - start)) + start;
        Function<Integer, Double> invConverter = i -> (double) (i - start) / (stop - start);
        return new Slider<>(width, height, xOffset, yOffset, parent, name, buffer, converter, invConverter);
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {
        ColorBox frame = new ColorBox(getWidth(), getHeight(), 0, 0, this, new Vector4f(0.375f, 0.375f, 0.375f, 1f));
        frame.draw(pxScale, matrix4f);

        TextBox description = new TextBox(getWidth() - 2, getHeight() - 2, 1, 1, this, this.name + ": " + value.getValue().toString(), true, true);

        scrollRail.draw(pxScale, matrix4f);
        knob.draw(pxScale, matrix4f);
        description.draw(pxScale, matrix4f);
    }

    public double getValue() {
        return sliderValue;
    }

    private class Knob extends ClickFrame {
        private boolean clicked;
        private int clickOffset;

        public Knob(int width, int height, int xOffset, int yOffset, Box parent) {
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
            int mouseX = Main.getActiveWindow().getCursorPos().getX().intValue();

            update(pxScale);
            if (pressed() && !clicked) {
                clicked = true;
                clickOffset = mouseX - getCornerX(pxScale);
            }
            if (!isClicking() && clicked) {
                clicked = false;
            }
            if (clicked) {
                int knobPos = mouseX - clickOffset;
                int scrollLength = getParent().getWidth(pxScale) - getWidth(pxScale);
                int leftEnd = getParent().getCornerX(pxScale), rightEnd = leftEnd + scrollLength;
                int framedLength = Math.max(leftEnd, Math.min(knobPos, rightEnd)) - leftEnd;
                value.setValue(converter.apply((double) framedLength / scrollLength));
                sliderValue = invConverter.apply(value.getValue());
            }

            Vector4f frameColor = clicked || isHovering(pxScale) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);

            ColorBox outerBox = new ColorBox(getWidth(), getHeight(), 0, 0, this, frameColor);
            outerBox.draw(pxScale, matrix4f);
            ImageBox center = new ImageBox(getWidth() - 2, getHeight() - 2, 1, 1, this, Image.loadImage("/img/stone.png"));
            center.draw(pxScale, matrix4f);
        }
    }
}
