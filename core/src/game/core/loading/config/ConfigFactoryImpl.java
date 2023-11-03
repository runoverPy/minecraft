package game.core.loading.config;

import mdk.settings.template.ConfigDetails;
import mdk.settings.template.ConfigFactory;

public class ConfigFactoryImpl implements ConfigFactory {
    @Override
    public ConfigDetails<Integer> seedQuery() {
        return null;
    }

    @Override
    public ConfigDetails<Integer> intSlider(int start, int stop, int step) {
        return null;
    }

    @Override
    public ConfigDetails<String> inputField(String query) {
        return null;
    }

    @Override
    public <T extends Enum<T>> ConfigDetails<T> button(Class<T> tClass) {
        return null;
    }
}
