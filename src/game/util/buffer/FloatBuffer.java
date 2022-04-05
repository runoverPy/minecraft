package game.util.buffer;

public class FloatBuffer extends NumericBuffer<Float> {
    private float value;

    public FloatBuffer(Float startValue, Runnable onUpdate) {
        super(startValue, onUpdate);
    }

    public FloatBuffer() {
        this(0f);
    }

    public FloatBuffer(Float startValue) {
        super(startValue);
    }
}
