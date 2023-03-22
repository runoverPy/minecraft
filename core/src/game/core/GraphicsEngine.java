package game.core;

import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL14.glBlendFuncSeparate;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class GraphicsEngine {
    private static int vao;

    public int getVAO() {
        return vao;
    }

    public void init() {
        GL.createCapabilities();
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        glPolygonOffset(1, 1);
        glLineWidth(2);

        glEnable(GL_BLEND);
        glEnable(GL_POLYGON_OFFSET_FILL);
        glEnable(GL_LINE_SMOOTH);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glEnable(GL_SCISSOR_TEST);
        glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE);
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
