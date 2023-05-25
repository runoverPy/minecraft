package game.assets.text;

import org.joml.Matrix4f;
import org.joml.Vector4f;

public class MonospaceFont extends GlyphMap {
    @Override
    public void drawGlyph(Matrix4f matrix4f, char c, Vector4f charColor) {

    }

    @Override
    public Glyph getGlyph(char c) {
        return null;
    }

    @Override
    public int getCharWidth(char chr) {
        return 0;
    }

    @Override
    public int getCharHeight(char chr) {
        return 0;
    }
}
