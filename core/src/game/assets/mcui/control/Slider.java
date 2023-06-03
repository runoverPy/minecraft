package game.assets.mcui.control;

import game.assets.event.MouseEvent;
import game.assets.event.dispatch.InputMap;
import game.assets.mcui.Align;
import game.assets.mcui.Component;
import game.assets.mcui.PixelComponent;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.ImageTile;
import game.assets.mcui.asset.TextTile;
import game.main.Main;
import game.util.Image;
import game.util.relay.ObjectRelay;
import game.window.CursorPosCallback;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class Slider<T> extends PixelComponent {
    private String name;

    private Knob knob;
    private ColorTile outerTile;
    private ColorTile innerTile;
    private TextTile description;
    private ImageTile restricted;

    private Transformer<T> transformer;
    private double innerValue;
    private ObjectRelay<T> valueRelay;

    public Slider() {
        this.innerValue = 0;

        outerTile = new ColorTile(new Vector4f());
        outerTile.setParent(this);
        innerTile = new ColorTile(new Vector4f(0.375f, 0.375f, 0.375f, 1f));
        innerTile.setParent(this);
        knob = new Knob();
        knob.setPxWidth(8);
        knob.setParent(this);
        description = new TextTile();
        description.setParent(this);
        description.setShaded(true);
        description.setAlign(Align.CENTER);
        restricted = new ImageTile(Image.loadImage("/img/restricted.png"));
        restricted.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transformer<T> getTransformer() {
        return transformer;
    }

    public void setTransformer(Transformer<T> transformer) {
        this.transformer = transformer;
    }

    public boolean hasTransformer() {
        return transformer != null;
    }

    public T getValue() {
        if (transformer == null) return null;
        return transformer.convertValue(innerValue);
    }

    public void setValue(T value) {
        if (transformer == null) return;
        innerValue = (transformer.reverseValue(value));
        if (valueRelay != null) valueRelay.setValue(value);
    }

    public ObjectRelay<T> getValueRelay() {
        return valueRelay;
    }

    public void setValueRelay(ObjectRelay<T> valueRelay) {
        this.valueRelay = valueRelay;
        setValue(valueRelay.getValue());
    }

    public boolean hasValueRelay() {
        return this.valueRelay != null;
    }

    @Override
    public void layout() {
        outerTile.setPxSize(getPxWidth(), getPxHeight());
        innerTile.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
        innerTile.setLayoutPos(getPxScale(), getPxScale());
        description.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
        description.setLayoutPos(getPxScale(), getPxScale());
        restricted.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
        restricted.setLayoutPos(getPxScale(), getPxScale());
        knob.setPxHeight(getPxHeight());
    }

    @Override
    public void render(Matrix4f matrix) {
        layoutIfScaleChanged();
        outerTile.render(matrix);
        innerTile.render(matrix);
        if (hasTransformer()) {
            knob.render(matrix);
            description.setText(name + ": " + getValue());
            description.render(matrix);
        } else {
            restricted.render(matrix);
        }
    }

    @Override
    public Component pick(double x, double y) {
        Component optionalKnob = knob.pick(x, y);
        if (optionalKnob != null)
            return optionalKnob;
        else
            return super.pick(x, y);
    }

    private class Knob extends PixelComponent {
        private final ColorTile outerTile;
        private final ImageTile innerTile;
        private final InputMap<Knob> inputMap;

        private final CursorPosCallback cursorPosCallback = (xPos, yPos) -> {

        };

        private boolean clicked;
        private int clickOffset;

        public Knob() {
            outerTile = new ColorTile(new Vector4f(0, 0, 0, 1));
            outerTile.setParent(this);
            innerTile = new ImageTile(Image.loadImage("/img/stone.png"));
            innerTile.setParent(this);

            inputMap = new InputMap<>(this);
            inputMap.addEventMapping(
              new InputMap.MouseMapping(MouseEvent.MOUSE_PRESSED, event -> {
                  clicked = true;
                  clickOffset = Main.getActiveWindow().getCursorPos().x().intValue() - getGlobalX();
              })
            );
        }

        @Override
        public void layout() {
            outerTile.setPxSize(getPxWidth(), getPxHeight());
            innerTile.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
            innerTile.setLayoutPos(getPxScale(), getPxScale());
        }

        @Override
        public int getLayoutX() {
            return (int) (innerValue * (Slider.this.getWidth() - getWidth()));
        }

        @Override
        public void render(Matrix4f matrix) {
            if (Main.getActiveWindow().getMouseButton(GLFW_MOUSE_BUTTON_LEFT) != GLFW_PRESS && clicked) {
                clicked = false;
            }

            Vector4f frameColor = clicked || isHovering() ?
              new Vector4f(1, 1, 1, 1) :
              new Vector4f(0, 0, 0, 1);
            outerTile.setBoxColor(frameColor);

            if (clicked) {
                int
                  mouseX = Main.getActiveWindow().getCursorPos().x().intValue(),
                  knobPos = mouseX - clickOffset,
                  totalLength = Slider.this.getWidth() - getWidth(),
                  left = Slider.this.getGlobalX(),
                  right = left + totalLength,
                  clampedPos = Math.max(left, Math.min(knobPos, right)) - left;

                double newValue = (double) clampedPos / totalLength;
                if (transformer != null) {
                    innerValue = transformer.roundValue(newValue);
                    if (valueRelay != null)
                        valueRelay.setValue(transformer.convertValue(newValue));
                }
            }

            layoutIfScaleChanged();
            outerTile.render(matrix);
            innerTile.render(matrix);
        }
    }

    public abstract static class Transformer<T> {
        protected abstract double roundValue(double value);

        protected abstract T convertValue(double value);
        protected abstract double reverseValue(T value);

    }

    public static final class IntTransformer extends Transformer<Integer> {
        private final int start, stop, step;

        public IntTransformer(int start, int stop, int step) {
            if (stop <= start) throw new IllegalArgumentException("cannot create transformer if stop <= start");
            if (step < 1) throw new IllegalArgumentException("value for step less than one, must be equal or greater");
            this.start = start;
            this.stop = stop;
            this.step = step;
        }

        public IntTransformer(int start, int stop) {
            this(start, stop, 1);
        }

        public IntTransformer(int stop) {
            this(0, stop, 1);
        }

        @Override
        protected double roundValue(double value) {
            int steps = (stop - start) / step;
            return Math.round(steps * value) / (double) steps;
        }

        @Override
        protected Integer convertValue(double value) {
            return (int) Math.round(value * (stop - start)) + start;
        }

        @Override
        protected double reverseValue(Integer value) {
            return (double) (value - start) / (stop - start);
        }

    }

    public static final class FloatTransformer extends Transformer<Float> {
        private final float start;
        private final float stop;
        private final int digits;

        public FloatTransformer(float start, float stop, int digits) {
            if (stop <= start) throw new IllegalArgumentException("cannot create transformer if stop <= start");
            this.start = start;
            this.stop = stop;
            this.digits = digits;
        }

        public FloatTransformer(float start, float stop) {
            this(start, stop, -1);
        }

        public FloatTransformer(float stop) {
            this(0, stop, -1);
        }

        public FloatTransformer() {
            this(0, 1, -1);
        }

        @Override
        protected double roundValue(double value) {
            if (digits >= 0) {
                int steps = (int) ((stop - start) * Math.pow(10, digits));
                return Math.round(steps * value) / (double) steps;
            } else return value;
        }

        @Override
        protected Float convertValue(double value) {
            if (digits >= 0) {
                int steps = (int) ((stop - start) * Math.pow(10, digits));
                return (float) ((float) Math.round(steps * value) / Math.pow(10, digits) + start);
            } else
                return (float) ((stop - start) * value + start);
        }

        @Override
        protected double reverseValue(Float value) {
            double smoothValue = (value - start) / (stop - start);
            return roundValue(smoothValue);
        }
    }

    public static final class EnumTransformer<T extends Enum<T>> extends Transformer<T> {
        private final Class<T> enumClass;

        public EnumTransformer(Class<T> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        protected double roundValue(double value) {
            int steps = enumClass.getEnumConstants().length - 1;
            return Math.round(steps * value) / (double) steps;
        }

        @Override
        protected T convertValue(double value) {
            int ordinal = (int) Math.round(value * (enumClass.getEnumConstants().length - 1));
            return enumClass.getEnumConstants()[ordinal];
        }

        @Override
        protected double reverseValue(T value) {
            return value.ordinal();
        }
    }

    public static final class ArrayTransformer<T> extends Transformer<T> {
        private final T[] values;

        public ArrayTransformer(T[] values) {
            this.values = values;
        }

        @Override
        protected double roundValue(double value) {
            int steps = values.length - 1;
            return Math.round(steps * value) / (double) steps;
        }

        @Override
        protected T convertValue(double value) {
            int index = (int) Math.round(value * (values.length - 1));
            return values[index];
        }

        @Override
        protected double reverseValue(T value) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == value) return i;
            }
            return 0;
        }
    }
}
