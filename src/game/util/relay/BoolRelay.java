package game.util.relay;

public class BoolRelay extends ObjectRelay<Boolean> {
    public BoolRelay(boolean value) {
        super(value);
    }

    public BoolRelay() {
        this(false);
    }
}
