package game.assets;

import game.main.Main;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL43;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glScissor;

public class GLUtils {
    public static GLOP scissor(int x, int y, int w, int h) {
        int winHeight = Main.getActiveWindow().getHeight();
        int[] oldMask = new int[4], newMask = new int[]{x, winHeight - y - h, w, h};
        glGetIntegerv(GL_SCISSOR_BOX, oldMask);
        return applyMask(GL43::glScissor, oldMask, newMask);
    }

    public static GLOP viewport(int x, int y, int w, int h) {
        int winHeight = Main.getActiveWindow().getHeight();
        int[] oldMask = new int[4], newMask = new int[]{x, winHeight - y - h, w, h};
        glGetIntegerv(GL_VIEWPORT, oldMask);
        return applyMask(GL43::glViewport, oldMask, newMask);
    }

    public static GLOP lineWidth(int newWidth) {
        int oldWidth = glGetInteger(GL_LINE_WIDTH);
        glLineWidth(newWidth);
        return () -> glLineWidth(oldWidth);
    }

    private static GLOP applyMask(MaskOp op, int[] oldMask, int[] newMask) {
        if (oldMask.length != 4 || newMask.length != 4) throw new IllegalArgumentException("Array length must be 4");
        op.apply(
          clamp(newMask[0], oldMask[0], oldMask[0] + oldMask[2]),
          clamp(newMask[1], oldMask[1], oldMask[1] + oldMask[3]),
          Math.max(0, Math.min(oldMask[0] + oldMask[2], newMask[0] + newMask[2]) - Math.max(oldMask[0], newMask[0])),
          Math.max(0, Math.min(oldMask[1] + oldMask[3], newMask[1] + newMask[3]) - Math.max(oldMask[1], newMask[1]))
        );
        return () -> op.apply(oldMask[0], oldMask[1], oldMask[2], oldMask[3]);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }

    public interface GLOP extends AutoCloseable {
        @Override
        void close();
    }

    private interface MaskOp {
        void apply(int x, int y, int w, int h);
    }


    /**
     * Used in try-with-resources blocks. All GL framebuffer writes performed within this block will be written to the
     * given framebuffer instead. At invocation of the close() function, the data written to the framebuffer will be
     * copied to the higher framebuffer. If these blocks are nested, the inner framebuffer will write to the outer
     * framebuffer.
     */
    class RenderToFramebuffer implements AutoCloseable {

        @Override
        public void close() throws Exception {

        }
    }


}
