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
}
