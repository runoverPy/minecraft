package game.assets.text;

import game.assets.PixelWidget;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class AsciiGlyphMap extends GlyphMap {
    public static final float aspectRatio = 6f/11;
    public static final int charWidth = 6;
    public static final int charHeight = 11;

    private static final float[] glyphVertices = new float[]{
            0, 0, -1,
            1, 0, -1,
            0, 1, -1,
            1, 1, -1,
    };
    private static final int vertexBuffer, texturePosBuffer, texture, program, transform, color;

    static {
        int width = 16, height = 6;
        float[] glyphTexture = new float[width * height * 8];
        float u, v;
        for (int i = 0; i < width * height; i++) {
            for (int j = 0; j < 4; j++) {
                u = (i % width + j % 2) / (float) width;
                v = (i / width + 1 - j / 2) / (float) height;
                glyphTexture[i * 8 + j * 2] = u;
                glyphTexture[i * 8 + j * 2 + 1] = v;
            }
        }
        vertexBuffer = Util.genArrayBuffer(glyphVertices);
        texturePosBuffer = Util.genArrayBuffer(glyphTexture);
        texture = Util.genTexture("/font/ascii/ascii.png");
        program = Util.genProgram(
                new Pair<>("/shaders/char-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/char-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
        color = glGetUniformLocation(program, "color");
    }

    public static void drawGlyph(Matrix4f matrix2D, char c, Vector4f charColor) {
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL);
        glBindBuffer(GL_ARRAY_BUFFER, texturePosBuffer);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 32L * getIndex(c));

        glBindTexture(GL_TEXTURE_2D, texture);
        glUseProgram(program);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            glUniform4fv(color, charColor.get(stack.mallocFloat(4)));
        }
        glUniformMatrix4fv(transform, false, matrix2D.get(new float[16]));

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

        glUseProgram(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public static void drawGlyph(Matrix4f matrixPV, char c) {
        drawGlyph(matrixPV, c, new Vector4f(1f, 1f, 1f, 1f));
    }

    private static int getIndex(char c) {
        if (c < ' ') {
            c = (char) 127;
        }
        if (c > (char) 127) c = (char) 127;
        return (int) c - 32;
    }
}
