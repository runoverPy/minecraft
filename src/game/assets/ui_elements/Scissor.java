package game.assets.ui_elements;

import static org.lwjgl.opengl.GL46.*;

public interface Scissor extends AutoCloseable {
    static Scissor cut(int x, int y, int w, int h) {
        return cut(new int[] {x, y, w, h});
    }

    static Scissor cut(int[] newScissor) {
        if (newScissor.length != 4) throw new IllegalArgumentException("Array length must be 4");
        int[] oldScissor = new int[4];
        glGetIntegerv(GL_SCISSOR_BOX, oldScissor);
        glScissor(
          clamp(oldScissor[0], newScissor[0], oldScissor[0] + oldScissor[2]),
          clamp(oldScissor[1], newScissor[1], oldScissor[1] + oldScissor[3]),
          Math.min(oldScissor[0] + oldScissor[2] - newScissor[0], newScissor[2]),
          Math.min(oldScissor[1] + oldScissor[3] - newScissor[1], newScissor[3])
        );
        return () -> glScissor(oldScissor[0], oldScissor[1], oldScissor[2], oldScissor[3]);
    }

    static int clamp(int a, int b, int c) {
        return Math.max(a, Math.min(b, c));
    }

    void close();
}
