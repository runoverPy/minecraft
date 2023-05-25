package game.util.relay;

public class IntRelay extends ObjectRelay<Integer> {
    public IntRelay(Integer startValue, Runnable onUpdate) {
        super(startValue);
    }

    public IntRelay(Integer startValue) {
        super(startValue);
    }

    public IntRelay() {
        this(0);
    }
}
