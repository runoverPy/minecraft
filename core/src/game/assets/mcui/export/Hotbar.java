package game.assets.mcui.export;

import game.assets.mcui.PixelComponent;
import game.assets.ui_elements.Component;
import game.assets.ui_elements.asset.ColorBox;
import game.assets.ui_elements.asset.ImageBox;
import game.core.rendering.RenderUtils;
import game.core.server.Block;
import game.main.Main;
import game.util.Image;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;

public class Hotbar extends PixelComponent {
    private static final int sideLen = 16;
    private static final int spacing = 2;

    private final String[] items;
    private int index;

    public static Hotbar demoHotbar() {
        Hotbar hotbar = new Hotbar();
        hotbar.setItem(0, "vanilla:stone");
        hotbar.setItem(1, "vanilla:dirt");
        hotbar.setItem(2, "vanilla:grass");
        hotbar.setItem(3, "vanilla:stone_bricks");
        hotbar.setItem(4, "vanilla:bricks");
        hotbar.setItem(5, "vanilla:steinle");
        hotbar.setItem(6, "vanilla:blumen");
        return hotbar;
    }

    public Hotbar() {
        this.items = new String[9];
        setResizeable(false);
    }

    public void select(int index) {
        checkSlot(index);
        this.index = index;
    }

    public void incSelect() {
        if (index == 8) index = 0;
        else index++;
    }

    public void decSelect() {
        if (index == 0) index = 8;
        else index--;
    }

    public String getItem() {
        return items[index];
    }

    public String getItem(int index) {
        checkSlot(index);
        return items[index];
    }

    public void setItem(String item) {
        items[index] = item;
    }

    public void setItem(int index, String item) {
        checkSlot(index);
        items[index] = item;
    }

    private void checkSlot(int index) throws IndexOutOfBoundsException {
        if (index < 0 || 8 < index)
            throw new IndexOutOfBoundsException("Invalid value " + index + ": only values between 0 and 8 allowed");
    }

    public void draw(Matrix4f matrix) {
        glDisable(GL_DEPTH_TEST);
        renderStandalone(matrix);
        glEnable(GL_DEPTH_TEST);
    }

    public void renderStandalone(Matrix4f matrix4f) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        int
          globalX = (Main.getActiveWindow().getWidth() - 164 * getPxScale()) / 2,
          globalY = Main.getActiveWindow().getHeight() - 20 * getPxScale(),
          pxScale = getPxScale();

        // uiel elements require a parent. this is a band-aid fix port.
        // fixme remove uiel objects
        Component virtualFrame = new Component() {
            @Override
            public int getWidth() {
                return Hotbar.this.getPxWidth();
            }

            @Override
            public int getHeight() {
                return Hotbar.this.getPxHeight();
            }

            @Override
            public int getWidth(int pxScale) {
                return Hotbar.this.getWidth();
            }

            @Override
            public int getHeight(int pxScale) {
                return Hotbar.this.getHeight();
            }

            @Override
            public int getCornerX(int pxScale) {
                return globalX;
            }

            @Override
            public int getCornerY(int pxScale) {
                return globalY;
            }

            @Override
            public Component getTopComponent(int x, int y, int pxScale) {
                return null;
            }
        };

//        ColorTile background2 = new ColorTile(new Vector4f(0.25f, 0.25f, 0.25f, 1f));
//        background2.setSize(164, sideLen);
//        background2.setLayoutPos(spacing, spacing);
//        background2.setParent(this);
//        background2.render(matrix4f);

        for (int i = 0; i < 9; i++) {
            int xOffset = (i + 1) * spacing + i * sideLen;

            ColorBox background = new ColorBox(sideLen, sideLen, xOffset, spacing, virtualFrame, new Vector4f(0.25f, 0.25f, 0.25f, 1f));
            background.draw(pxScale, matrix4f);

            if (items[i] != null && !Objects.equals(items[i], "")) {
                glViewport(
                  globalX + xOffset * pxScale,
                  spacing * pxScale,
                  sideLen * pxScale,
                  sideLen * pxScale
                );
                RenderUtils.drawBlockIcon(new Block(items[i]).getMaterial());
                glViewport(0, 0, winWidth, winHeight);
            }
        }

        ImageBox frame = new ImageBox(164, 20, 0, 0, virtualFrame, Image.loadImage("/img/hotbar_frame.png"));
        frame.draw(pxScale, matrix4f);
        int xOffset = index * (spacing + sideLen);
        ImageBox selectFrame = new ImageBox(20, 20, xOffset, 0, virtualFrame, Image.loadImage("/img/hotbar_select_frame.png"));
        selectFrame.draw(pxScale, matrix4f);
    }

    @Override
    public void render(Matrix4f matrix) {
        // fixme
    }
}
