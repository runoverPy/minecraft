package noise;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NoiseLayers {
    public static void main(String[] args) throws IOException {
        final int WIDTH = 256, HEIGHT = 256;
        final double FEATURE_SIZE = 48;
        final int STEPS = 16;

        NoiseLayers noise = new NoiseLayers(System.currentTimeMillis(), 10, FEATURE_SIZE, 0.4, 0.3);

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                double value = Math.floor(noise.eval(x, y) * STEPS) / STEPS;
                int rgb = 0x010101 * (int) (value * 255);
                image.setRGB(x, y, rgb);
            }
        }
        ImageIO.write(image, "png", new File("noise.png"));
    }

    private final NoiseLayer[] layers;
    private final double maxAmplitude;

    public NoiseLayers(long seed, int layers, double featureSize, final double featureSizeFactor, final double amplitudeFactor)  {
        this.layers = new NoiseLayer[layers];
        double amplitude = 1;
        double maxAmplitude = 0;
        Random seedGenerator = new Random(seed);
        for (int i = 0; i < layers; i++) {
            this.layers[i] = new NoiseLayer(new OpenSimplexNoise(seedGenerator.nextLong()), featureSize, amplitude);
            maxAmplitude += amplitude;
            featureSize *= featureSizeFactor;
            amplitude *= amplitudeFactor;
        }
        this.maxAmplitude = maxAmplitude;
    }

    public double eval(double x, double y) {
        double value = 0;
        for (NoiseLayer layer : layers)
            value += layer.eval(x, y);
        return value / maxAmplitude;
    }

    public double eval(double x, double y, double z) {
        double value = 0;
        for (NoiseLayer layer : layers)
            value += layer.eval(x, y, z);
        return value / maxAmplitude;
    }

    public double eval(double x, double y, double z, double w) {
        double value = 0;
        for (NoiseLayer layer : layers)
            value += layer.eval(x, y, z, w);
        return value / maxAmplitude;
    }

    private record NoiseLayer(OpenSimplexNoise generator, double featureSize, double amplitude) {
        public double eval(double x, double y) {
            return (generator.eval(x / featureSize, y / featureSize) + 1) / 2 * amplitude;
        }

        public double eval(double x, double y, double z) {
            return (generator.eval(x / featureSize, y / featureSize, z / featureSize) + 1) / 2 * amplitude;
        }

        public double eval(double x, double y, double z, double w) {
            return (generator.eval(x / featureSize, y / featureSize, z / featureSize, w / featureSize) + 1) / 2 * amplitude;
        }
    }
}
