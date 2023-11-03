package mdk.settings.template;

public interface ConfigFactory {
    ConfigDetails<Integer> seedQuery();

    default ConfigDetails<Integer> intSlider(int stop) {
        return intSlider(0, stop, 1);
    }
    default ConfigDetails<Integer> intSlider(int start, int stop) {
        return intSlider(start, stop, 1);
    }
    ConfigDetails<Integer> intSlider(int start, int stop, int step);

    ConfigDetails<String> inputField(String query);

    <T extends Enum<T>> ConfigDetails<T> button(Class<T> tClass);
}
