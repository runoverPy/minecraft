package game.util;

import org.lwjgl.glfw.GLFWImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;

import static org.lwjgl.BufferUtils.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;


public class Image {
    private final int format;
    private final Raster raster;

    private static final Map<String, Image> loadedImages = new HashMap<>();

    public static Image loadImage(String filePath) {
        if (loadedImages.containsKey(filePath)) return loadedImages.get(filePath);
        else {
            Image newImage = new Image(filePath);
            loadedImages.put(filePath, newImage);
            return newImage;
        }
    }

    private Image(String filePath) {
        try {
            InputStream resource = getClass().getResourceAsStream(filePath);
            if (resource == null) throw new FileNotFoundException("Resource not found: " + getClass().getResource(filePath));
            BufferedImage imgBuffer = ImageIO.read(resource);
            format = (imgBuffer.getAlphaRaster() != null ? 4 : 3);
            raster = imgBuffer.getData();

        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException();
        }
    }

    public int getWidth() {
        return raster.getWidth();
    }

    public int getHeight() {
        return raster.getHeight();
    }

    public int getFormat() {
        return format;
    }

    public float[] getImg() {
        float[] unscaledImg = raster.getPixels(0, 0, getWidth(), getHeight(), new float[getWidth() * getHeight() * format]);
        float[] img = new float[getWidth() * getHeight() * format];
        for (int i = 0; i < unscaledImg.length; i++) {
            img[i] = Math.round(unscaledImg[i]) / 256.0f;
        }
        return img;
    }

    public GLFWImage getImageBuffer() {
        byte[] iconBytes = ((DataBufferByte) raster.getDataBuffer()).getData();
        ByteBuffer buffer = createByteBuffer(iconBytes.length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(iconBytes, 0, iconBytes.length);
        buffer.flip();
        return GLFWImage.create().set(getWidth(), getHeight(), buffer);
    }

    public static GLFWImage.Buffer collect(GLFWImage... images) {
        GLFWImage.Buffer buffer = GLFWImage.create(images.length);
        for (int i = 0; i < images.length; i++) {
            buffer.put(i, images[i]);
        }
        return buffer;
    }
}