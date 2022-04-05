package game.assets.widgets;

import game.core.rendering.Renderer;
import game.main.Main;
import game.mechanics.blocks.Block;
import game.util.Image;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.Objects;

import static org.lwjgl.opengl.GL46.*;

public class Hotbar extends FlexBox {
    private static final int sideLen = 16;
    private static final int spacing = 2;

    private final String[] items;
    private int selectedSlot;
    ImageBox frame;

    public Hotbar() {
        super(164, 20);
        this.items = new String[9];
        this.frame = new ImageBox(getWidth(), getHeight(), 0, 0, this, Image.loadImage("/img/hotbar_frame.png"));
    }

    public static Hotbar demoHotbar() {
        Hotbar hotbar = new Hotbar();
        hotbar.setItem(0, "vanilla::stone");
        hotbar.setItem(1, "vanilla::dirt");
        hotbar.setItem(2, "vanilla::grass");
        hotbar.setItem(3, "vanilla::stone_bricks");
        hotbar.setItem(4, "vanilla::bricks");
        hotbar.setItem(5, "vanilla::steinle");
        return hotbar;
    }

    public void select(int slot) {
        checkSlot(slot);
        selectedSlot = slot;
    }

    public void incSelect() {
        if (selectedSlot == 8) selectedSlot = 0;
        else selectedSlot++;
    }

    public void decSelect() {
        if (selectedSlot == 0) selectedSlot = 8;
        else selectedSlot--;
    }

    public void setItem(int slot, String item) {
        this.items[slot] = item;
    }

    public void setItem(String item) {
        this.items[selectedSlot] = item;
    }

    public String getItem(int slot) {
        return items[slot];
    }

    public String getItem() {
        return items[selectedSlot];
    }

    private void checkSlot(int slot) throws RuntimeException {
        if (0 > slot || slot > 8) throw new RuntimeException("Invalid value " + slot + ": only values between 0 and 8 allowed");
    }

    @Override
    public void draw(Matrix4f matrix4f) {
        glDisable(GL_DEPTH_TEST);
        int pxScale = getPixelScale();
        draw(pxScale, matrix4f);
        glEnable(GL_DEPTH_TEST);
    }

    @Override
    public int getCornerY(int pxScale) {
        return Main.getActiveWindow().getHeight() - 20 * pxScale;
    }

    @Override
    public int getPixelScale() {
        return super.getPixelScale() / 2;
    }

    public void draw(int pxScale, Matrix4f matrix4f) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();

        for (int i = 0; i < 9; i++) {
            int xOffset = (i + 1) * spacing + i * sideLen;

            ColorBox background = new ColorBox(sideLen, sideLen, xOffset, spacing, this, new Vector4f(0.25f, 0.25f, 0.25f, 1f));
            background.draw(pxScale, matrix4f);

            if (items[i] != null && !Objects.equals(items[i], "")) {
                glViewport(
                        getCornerX(pxScale) + xOffset * pxScale,
                        spacing * pxScale,
                        sideLen * pxScale,
                        sideLen * pxScale
                );
                Renderer.drawBlockIcon(new Block(items[i]).getMaterial());
                glViewport(0, 0, winWidth, winHeight);
            }
        }

        frame.draw(pxScale, matrix4f);
        int xOffset = selectedSlot * (spacing + sideLen);
        ImageBox selectFrame = new ImageBox(20, 20, xOffset, 0, this, Image.loadImage("/img/hotbar_select_frame.png"));
        selectFrame.draw(pxScale, matrix4f);
    }
}
