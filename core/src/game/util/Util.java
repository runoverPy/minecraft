package game.util;

import static org.lwjgl.opengl.GL46.*;
import org.javatuples.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;


public class Util {
    @SafeVarargs
    public static int genProgram(Pair<String, Integer>... programShaders) {
        int[] shaders = new int[programShaders.length];
        int programID = glCreateProgram();

        for (int i = 0; i < programShaders.length; i++) {
            shaders[i] = genShader(programShaders[i].getValue0(), programShaders[i].getValue1());
            glAttachShader(programID, shaders[i]);
        }
        glLinkProgram(programID);
        if(glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException(glGetProgramInfoLog(programID));
        }
        for (int shaderID: shaders) {
            glDetachShader(programID, shaderID);
        }
        return programID;
    }

    private static int genShader(String shaderPath, int shaderType) {
        try {
            char[] buffer = new char[1024];
            StringBuilder stringBuilder = new StringBuilder();
            InputStream stream = Util.class.getResourceAsStream(shaderPath);
            assert stream != null;
            Reader in = new InputStreamReader(stream, StandardCharsets.UTF_8);
            for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0;) {
                stringBuilder.append(buffer, 0, numRead);
            }
            String shaderData = stringBuilder.toString();
            
            int shaderID = glCreateShader(shaderType);
            glShaderSource(shaderID, shaderData);
            glCompileShader(shaderID);
            if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
                String err = glGetShaderInfoLog(shaderID);
                throw new RuntimeException("GLSL Compile Error in file " + shaderPath + "\n" + err);
            }
            return shaderID;
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
            return 0;
        }
    }

    public static void updateTexture(Image image, int texture) {
        int format = image.getFormat() == 4 ? GL_RGBA : GL_RGB;
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexImage2D(
          GL_TEXTURE_2D,
          0,
          GL_RGBA,
          image.getWidth(),
          image.getHeight(),
          0,
          format,
          GL_FLOAT,
          image.getImg()
        );
    }

    public static int genTexture(Image texture, int target, int wrap_s, int wrap_t, int tex_mag, int tex_min) {
        int textureID = glGenTextures();
        int format = texture.getFormat() == 4 ? GL_RGBA : GL_RGB;
        glBindTexture(target, textureID);
        glTexImage2D(
                target,
                0,
                GL_RGBA,
                texture.getWidth(),
                texture.getHeight(),
                0,
                format,
                GL_FLOAT,
                texture.getImg()
        );
        glTexParameteri(target, GL_TEXTURE_WRAP_S, wrap_s);
        glTexParameteri(target, GL_TEXTURE_WRAP_T, wrap_t);
        glTexParameteri(target, GL_TEXTURE_MAG_FILTER, tex_mag);
        glTexParameteri(target, GL_TEXTURE_MIN_FILTER, tex_min);
        glGenerateMipmap(target);
        return textureID;
    }

    public static int genTexture(String source, int target, int wrap_s, int wrap_t, int tex_mag, int tex_min) {
        ImageFile texture = ImageFile.loadImage(source);
        return genTexture(texture, target, wrap_s, wrap_t, tex_mag, tex_min);
    }

    public static int genTexture(Image texture, int tex_mag, int tex_min) {
        return genTexture(texture, GL_TEXTURE_2D, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE, tex_mag, tex_min);
    }

    public static int genTexture(String source, int tex_mag, int tex_min) {
        return genTexture(source, GL_TEXTURE_2D, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE, tex_mag, tex_min);
    }

    public static int genTexture(Image texture) {
        return genTexture(texture, GL_TEXTURE_2D, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE, GL_NEAREST, GL_NEAREST);
    }

    public static int genTexture(String source) {
        return genTexture(source, GL_TEXTURE_2D, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE, GL_NEAREST, GL_NEAREST);
    }

    public static int genArrayBuffer(float[] vertices) {
        int buffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return buffer;
    }
}
