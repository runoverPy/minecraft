package game.util.relay;

public class FloatRelay extends ObjectRelay<Float> {
    private float value;

    public FloatRelay(Float startValue, Runnable onUpdate) {
        super(startValue);
    }

    public FloatRelay() {
        this(0f);
    }

    public FloatRelay(Float startValue) {
        super(startValue);
    }
}
