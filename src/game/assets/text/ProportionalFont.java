package game.assets.text;

import game.util.Util;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class ProportionalFont extends GlyphMap{
    private static final float[] glyphVertices = new float[]{
            0, -1, -1,
            1, -1, -1,
            0, 0, -1,
            1, 0, -1,
    };

    public static final int chrHeight = 10;

    private final Map<Character, Glyph> glyphs;
    private final Glyph missingGlyph;
    private final int vertexBuffer, texturePosBuffer, texture, program, transform, color;

    public ProportionalFont() throws DocumentException {
        glyphs = new HashMap<>();

        SAXReader reader = new SAXReader();
        Document document = reader.read(getClass().getResourceAsStream("/font/prop/font.xml"));
        Element font = document.getRootElement();

        int charCount = Integer.parseInt(font.valueOf("@charcount"));
        int imgWidth = Integer.parseInt(font.valueOf("@imgWidth"));
        int imgHeight = Integer.parseInt(font.valueOf("@imgHeight"));

        float[] glyphTexture = new float[charCount * 8];

        int i = 0;
        for (Node character : font.selectNodes("./char")) {
            char chr = character.getText().charAt(0);
            int chrX = Integer.parseInt(character.valueOf("@cX"));
            int chrY = Integer.parseInt(character.valueOf("@cY"));
            int chrW = Integer.parseInt(character.valueOf("@w"));
            int chrH = 10;

            glyphs.put(chr, new Glyph(chrW, 10, i));

            float[] subarray = new float[]{
                    (float) chrX / imgWidth,
                    (float) (chrY + chrH) / imgHeight,
                    (float) (chrX + chrW) / imgWidth,
                    (float) (chrY + chrH) / imgHeight,
                    (float) chrX / imgWidth,
                    (float) chrY / imgHeight,
                    (float) (chrX + chrW) / imgWidth,
                    (float) chrY / imgHeight,

            };
            System.arraycopy(subarray, 0, glyphTexture, i * 8, subarray.length);
            i++;
        }

        Node missingChar = font.selectSingleNode("./missingchar");
        int chrX = Integer.parseInt(missingChar.valueOf("@cX"));
        int chrY = Integer.parseInt(missingChar.valueOf("@cY"));
        int chrW = Integer.parseInt(missingChar.valueOf("@w"));
        int chrH = 10;

        missingGlyph = new Glyph(chrW, 10, i);

        float[] subarray = new float[]{
                (float) chrX / imgWidth,
                (float) (chrY + chrH) / imgHeight,
                (float) (chrX + chrW) / imgWidth,
                (float) (chrY + chrH) / imgHeight,
                (float) chrX / imgWidth,
                (float) chrY / imgHeight,
                (float) (chrX + chrW) / imgWidth,
                (float) chrY / imgHeight,

        };
        System.arraycopy(subarray, 0, glyphTexture, i * 8, subarray.length);

        vertexBuffer = Util.genArrayBuffer(glyphVertices);
        texturePosBuffer = Util.genArrayBuffer(glyphTexture);
        texture = Util.genTexture("/font/prop/ascii.png");
        program = Util.genProgram(
                new Pair<>("/shaders/char-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/char-fs.glsl", GL_FRAGMENT_SHADER)
        );
        transform = glGetUniformLocation(program, "transform");
        color = glGetUniformLocation(program, "color");
    }

    public void drawGlyph(Matrix4f matrix4f, char c, Vector4f charColor) {
        Glyph glyph = glyphs.get(c);
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL);
        glBindBuffer(GL_ARRAY_BUFFER, texturePosBuffer);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 32L * glyph.getIndex());

        glBindTexture(GL_TEXTURE_2D, texture);
        glUseProgram(program);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            glUniform4fv(color, charColor.get(stack.mallocFloat(4)));
        }
        glUniformMatrix4fv(transform, false, matrix4f.get(new float[16]));

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

        glUseProgram(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void drawGlyph(Matrix4f matrix4f, char c) {
        drawGlyph(matrix4f, c, new Vector4f(1f));
    }

    public Glyph getGlyph(char c) {
        return glyphs.get(c);
    }

    public int getCharWidth(char chr) {
        return glyphs.get(chr).getW();
    }

    public int getCharHeight(char chr) {
        return glyphs.get(chr).getH();
    }

    public static class Glyph {
        private final int w, h;
        private final int index;

        private Glyph(int w, int h, int index) {
            this.w = w;
            this.h = h;
            this.index = index;
        }

        public int getW() {
            return w;
        }

        public int getH() {
            return h;
        }

        public int getIndex() {
            return index;
        }
    }
}
