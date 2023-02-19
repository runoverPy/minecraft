package game.util.relay;

public class EnumRelay<E extends Enum<E>> {
    private E value;

    public EnumRelay(E value) {
        this.value = value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }
}
