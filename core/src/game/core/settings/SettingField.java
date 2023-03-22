package game.core.settings;

public class SettingField<T> {
    private T value;

    public SettingField(Object initialValue) throws ClassCastException {
        this.value = (T) initialValue;
        System.out.println("casting to " + this.value.getClass());
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
