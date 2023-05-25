package game.util.relay;

public class DoubleRelay extends ObjectRelay<Double> {
    public DoubleRelay(Double startValue, Runnable onUpdate) {
        super(startValue);
    }

    public DoubleRelay(Double startValue) {
        super(startValue);
    }

    public DoubleRelay() {
        this(0d);
    }
}
