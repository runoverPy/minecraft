package game.assets.menus;

import game.assets.mcui.Align;
import game.assets.mcui.atoms.PixelImageTile;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.Slider;
import game.util.Image;
import game.util.Util;
import game.util.relay.DoubleRelay;
import game.util.relay.IntRelay;
import noise.NoiseLayers;
import org.joml.Matrix4f;

public class NoiseTest extends Menu {
    private final int WIDTH = 192, HEIGHT = 192, STEPS = 32;

    public NoiseTest(MenuHandler handler) {
        IntRelay layers = new IntRelay(1);
        IntRelay featureSize = new IntRelay(48);
        DoubleRelay featureSizeFactor = new DoubleRelay();
        DoubleRelay amplitudeFactor = new DoubleRelay();

        long seed = System.currentTimeMillis();

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        PixelImageTile noiseWindow = new PixelImageTile(new Image() {
            @Override
            public int getWidth() {
                return WIDTH;
            }

            @Override
            public int getHeight() {
                return HEIGHT;
            }

            @Override
            public int getFormat() {
                return 3;
            }

            @Override
            public float[] getImg() {
                NoiseLayers noise = new NoiseLayers(seed, layers.get(), featureSize.get(), featureSizeFactor.get(), amplitudeFactor.get());
                float[] image = new float[WIDTH * HEIGHT * 3];
                for (int i = 0; i < WIDTH; i++) {
                    for (int j = 0; j < HEIGHT; j++) {
                        image[(i + j * WIDTH) * 3] = image[(i + j * WIDTH) * 3 + 1] = image[(i + j * WIDTH) * 3 + 2]
                          = (float) Math.floor(noise.eval(i, j) * STEPS) / STEPS;
                    }
                }
                return image;
            }
        }) {
            @Override
            public void render(Matrix4f matrix) {
                Util.updateTexture(image, texture);
                super.render(matrix);
            }
        };
        noiseWindow.setSize(WIDTH, HEIGHT);

        Slider<Integer> layersSlider = new Slider<>();
        layersSlider.setName("Layers");
        layersSlider.setTransformer(new Slider.IntTransformer(1, 100));
        layersSlider.setValueRelay(layers);
        layersSlider.setPxSize(192, 16);
        Slider<Integer> featureSizeSlider = new Slider<>();
        featureSizeSlider.setName("FeatureSize");
        featureSizeSlider.setTransformer(new Slider.IntTransformer(1, 256));
        featureSizeSlider.setValueRelay(featureSize);
        featureSizeSlider.setPxSize(192, 16);
        Slider<Double> featureSizeFactorSlider = new Slider<>();
        featureSizeFactorSlider.setName("FeatureSizeFactor");
        featureSizeFactorSlider.setTransformer(new Slider.DoubleTransformer(-1, 1, 2));
        featureSizeFactorSlider.setValueRelay(featureSizeFactor);
        featureSizeFactorSlider.setPxSize(192, 16);
        Slider<Double> amplitudeFactorSlider = new Slider<>();
        amplitudeFactorSlider.setName("AmplitudeFactor");
        amplitudeFactorSlider.setTransformer(new Slider.DoubleTransformer(-1, 1, 2));
        amplitudeFactorSlider.setValueRelay(amplitudeFactor);
        amplitudeFactorSlider.setPxSize(192, 16);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::prev);
        returnButton.setPxSize(192, 16);

        innerContainer.getChildren()
          .addAll(noiseWindow, layersSlider, featureSizeSlider, featureSizeFactorSlider, amplitudeFactorSlider, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
