package game.util;

import org.lwjgl.glfw.GLFWImage;

public interface Image {
    static GLFWImage.Buffer collect(GLFWImage... images) {
        GLFWImage.Buffer buffer = GLFWImage.create(images.length);
        for (int i = 0; i < images.length; i++) {
            buffer.put(i, images[i]);
        }
        return buffer;
    }

    int getWidth();

    int getHeight();

    int getFormat();

    float[] getImg();
}
