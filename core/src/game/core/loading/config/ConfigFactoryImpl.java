package game.core.loading.config;

import game.assets.mcui.Align;
import game.assets.mcui.Component;
import game.assets.mcui.atoms.TextTile;
import game.assets.mcui.container.HorizontalContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.InputField;
import mdk.settings.annotation.TextField;
import mdk.settings.template.ConfigDetails;
import mdk.settings.template.ConfigFactory;

import java.util.Random;

public class ConfigFactoryImpl implements ConfigFactory {
    private static final Random random = new Random();

    @Override
    public AbstractConfigDetails<Long> seedQuery() {
        return new AbstractConfigDetails<>(Long.class) {
            long initValue = random.nextLong();

            @Override
            public Component getUIElement() {
                VerticalContainer container = new VerticalContainer();
                container.setSize(-1, -1);
                container.setSpacing(2);
                InputField seedInputField = new InputField();
                seedInputField.setContents(Long.toHexString(initValue));
                seedInputField.setPxSize(107, 16);
                HorizontalContainer botContainer = new HorizontalContainer() {};
                botContainer.setSize(-1, -1);
                botContainer.setSpacing(2);
                TextTile description = new TextTile("Seed: " + getName());
                description.setHeight(16);
                description.setAlign(Align.CENTER_LEFT);
                Button reRollInitValue = new Button("Re-Roll Seed");
                reRollInitValue.setPxSize(83, 16);
                reRollInitValue.setOnAction(() -> {
                    initValue = random.nextLong();
                    seedInputField.setContents(Long.toHexString(initValue));
                });
                botContainer.getChildren().addAll(seedInputField, reRollInitValue);
                container.getChildren().addAll(description, botContainer);

                return container;
            }
        };
    }

    @Override
    public AbstractConfigDetails<Integer> intSlider(int start, int stop, int step) {
        return new AbstractConfigDetails<>(Integer.class) {
            @Override
            public Component getUIElement() {
                VerticalContainer verticalContainer = new VerticalContainer();
                verticalContainer.setSize(-1, -1);
                verticalContainer.setSpacing(2);
                TextTile description = new TextTile("Slider: " + getName());
                return new VerticalContainer();
            }
        };
    }

    @Override
    public AbstractConfigDetails<String> inputField(String query) {
        return null;
    }

    @Override
    public <T extends Enum<T>> AbstractConfigDetails<T> button(Class<T> tClass) {
        return null;
    }
}
