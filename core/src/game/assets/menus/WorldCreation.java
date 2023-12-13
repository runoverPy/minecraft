package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.Align;
import game.assets.mcui.container.HorizontalContainer;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.core.GameRuntime;
import game.core.loading.GeneratorBuilder;
import game.core.loading.config.AbstractConfigDetails;
import game.core.loading.config.ConfigTemplateImpl;
import mdk.settings.template.ConfigDetails;

import java.util.Map;

public class WorldCreation extends Menu {
    public WorldCreation(MenuHandler handler) {
        super(Background.DIRT);

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        VerticalContainer genSettings = new VerticalContainer();
        if (GameRuntime.getInstance().getGeneratorRegister().isGeneratorRegistered("vanilla:flat_test")) {
            GeneratorBuilder testGeneratorBuilder = GameRuntime
              .getInstance()
              .getGeneratorRegister()
              .getGeneratorBuilder("vanilla:flat_test");
            Map<String, ConfigDetails<?>> generatorConfigFields = testGeneratorBuilder.getGeneratorTemplate().getFields();
            for (Map.Entry<String, ConfigDetails<?>> configField : generatorConfigFields.entrySet()) {
                System.err.println("Adding SettingsField for generator parameter " + configField.getKey());
                if (configField.getValue() instanceof AbstractConfigDetails<?> configDetailsImpl) {
                    genSettings.getChildren().add(configDetailsImpl.getUIElement());
                }
            }
        }
        genSettings.setAlign(Align.TOP_CENTER);
        genSettings.setSize(-1, -1);
//        genSettings.setFillWidth(true);
        genSettings.setSpacing(2);
        HorizontalContainer line0 = new HorizontalContainer();
        line0.setAlign(Align.CENTER);
        line0.setSize(-1, -1);
        line0.setSpacing(2);
        Button enterGameButton = new Button("Enter Game");
        enterGameButton.setOnAction(() -> {});
        enterGameButton.setPxSize(95, 16);
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(() -> {});
        newGameButton.setPxSize(95, 16);
        line0.getChildren()
            .addAll(enterGameButton, newGameButton);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::prev);
        returnButton.setPxSize(192, 16);

        innerContainer.getChildren()
          .addAll(genSettings, line0, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
