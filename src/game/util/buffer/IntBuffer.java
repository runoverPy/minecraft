package game.util.buffer;

public class IntBuffer extends NumericBuffer<Integer> {
    public IntBuffer() {
        this(0);
    }

    public IntBuffer(Integer startValue, Runnable onUpdate) {
        super(startValue, onUpdate);
    }

    public IntBuffer(Integer startValue) {
        super(startValue);
    }
}
