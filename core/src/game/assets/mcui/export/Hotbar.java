package game.assets.mcui.export;

import game.assets.GLUtils;
import game.assets.mcui.PixelComponent;
import game.assets.mcui.atoms.ColorTile;
import game.assets.mcui.atoms.PixelImageTile;
import game.core.rendering.RenderUtils;
import game.core.server.Block;
import game.main.Main;
import game.util.ImageFile;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Hotbar extends PixelComponent {
    private static final int sideLen = 16;
    private static final int spacing = 2;

    private final String[] items;
    private int index;

    private ColorTile background;
    private PixelImageTile frame;
    private PixelImageTile select;

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
        background = new ColorTile(new Vector4f(0.25f, 0.25f, 0.25f, 1f));
        background.setPxSize(164, 20);
        background.setParent(this);
        frame = new PixelImageTile(ImageFile.loadImage("/img/hotbar_frame.png"));
        frame.setPxSize(164, 20);
        frame.setParent(this);
        select = new PixelImageTile(ImageFile.loadImage("/img/hotbar_select_frame.png"));
        select.setPxSize(20, 20);
        select.setParent(this);
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
        render(matrix);
        glEnable(GL_DEPTH_TEST);
    }

    @Override
    public int getLayoutX() {
        return (Main.getActiveWindow().getWidth() - 164 * getPxScale()) / 2;
    }

    @Override
    public int getLayoutY() {
        return Main.getActiveWindow().getHeight() - 20 * getPxScale();
    }

    @Override
    public void render(Matrix4f matrix) {
        int winWidth = Main.getActiveWindow().getWidth();
        int winHeight = Main.getActiveWindow().getHeight();
        int
          globalX = getGlobalX(),
          globalY = getGlobalY(),
          pxScale = getPxScale();

        background.render(matrix);
        // fixme
        for (int i = 0; i < 9; i++) {
            int xOffset = (i + 1) * spacing + i * sideLen;

            if (items[i] != null && !Objects.equals(items[i], "")) {
                try (GLUtils.GLOP ignored = GLUtils.viewport(
                  globalX + xOffset * pxScale,
                  globalY + spacing * pxScale,
                  sideLen * pxScale,
                  sideLen * pxScale
                )) {
                    RenderUtils.drawBlockIcon(new Block(items[i]).getMaterial());
                }
            }
        }
        frame.render(matrix);
        int xOffset = index * (spacing + sideLen) * getPxScale();
        select.setLayoutX(xOffset);
        select.render(matrix);
    }
}
