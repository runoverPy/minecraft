package game.util.buffer;

public class EnumBuffer<E extends Enum<E>> {
    private E value;

    public EnumBuffer(E value) {
        this.value = value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }
}
