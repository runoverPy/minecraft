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


public class ImageFile implements Image {
    private final int format;
    private final Raster raster;
    private final String filePath;

    private static final Map<String, ImageFile> loadedImages = new HashMap<>();

    public static ImageFile loadImage(String filePath) {
        if (loadedImages.containsKey(filePath)) return loadedImages.get(filePath);
        else {
            ImageFile newImage = new ImageFile(filePath);
            loadedImages.put(filePath, newImage);
            return newImage;
        }
    }

    private ImageFile(String filePath) {
        try {
            InputStream resource = getClass().getResourceAsStream(filePath);
            if (resource == null) throw new FileNotFoundException("Resource not found: " + getClass().getResource(filePath));
            BufferedImage imgBuffer = ImageIO.read(resource);
            format = (imgBuffer.getAlphaRaster() != null ? 4 : 3);
            raster = imgBuffer.getData();
            this.filePath = filePath;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int getWidth() {
        return raster.getWidth();
    }

    @Override
    public int getHeight() {
        return raster.getHeight();
    }

    @Override
    public int getFormat() {
        return format;
    }

    @Override
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

    public String getFilePath() {
        return filePath;
    }
}