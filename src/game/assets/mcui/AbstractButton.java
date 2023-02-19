package game.assets.mcui;

import game.core.GLFWWindow;
import game.assets.mcui.asset.ColorFrame;
import game.assets.mcui.asset.ImageFrame;
import game.assets.mcui.asset.TextField;
import game.main.Main;
import game.util.Image;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.function.Supplier;

public abstract class AbstractButton extends PixelComponent {
    protected static final Supplier<Boolean> TRUE = () -> true, FALSE = () -> false;

    protected Supplier<Boolean> isActive;

    private final ColorFrame buttonCover;
    private final ColorFrame buttonFrame;
    private final ImageFrame buttonImage;
    private final TextField description;

    protected static final Image filler = Image.loadImage("/img/stone.png");

    protected AbstractButton(String desc, Supplier<Boolean> activeCondition) {
        this.isActive = activeCondition;
        buttonCover = new ColorFrame(new Vector4f(0, 0, 0, 0.5f));
        buttonFrame = new ColorFrame(new Vector4f());
        buttonImage = new ImageFrame(filler);
        description = new TextField(desc);
        buttonCover.setDimensions(getAbsWidth(), getAbsHeight());
        buttonFrame.setDimensions(getAbsWidth(), getAbsHeight());
        buttonImage.setDimensions(getAbsWidth() - 2, getAbsHeight() - 2);
        description.setDimensions(getAbsWidth() - 2, getAbsHeight() - 2);
    }

    protected AbstractButton(String desc) {
        this(desc, TRUE);
    }

    public boolean isActive() {
        return isActive.get();
    }

    @Override
    public void render(Matrix4f matrix4f, int cornerX, int cornerY) {
        if (isActive()) {
            Vector4f frameColor = isHovering(cornerX, cornerY) ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);
            buttonFrame.setBoxColor(frameColor);
        }

        int pxScale = getPxScale();
        buttonFrame.render(matrix4f, cornerX, cornerY);
        buttonImage.render(matrix4f, cornerX + pxScale, cornerY + pxScale);
        description.render(matrix4f, cornerX + pxScale, cornerY + pxScale);
        if (!isActive()) {
            buttonCover.render(matrix4f, cornerX, cornerY);
        }
        System.out.println(buttonImage.getAbsWidth() + " " + buttonImage.getHeight());
    }

    @Override
    protected void onResize(int newWidth, int newHeight) {
        buttonCover.setDimensions(newWidth, newHeight);
        buttonFrame.setDimensions(newWidth, newHeight);
        buttonImage.setDimensions(newWidth - 2, newHeight - 2);
        description.setDimensions(newWidth - 2, newHeight - 2);
    }

    @Override
    protected int minWidth() {
        return 2;
    }

    @Override
    protected int minHeight() {
        return 2;
    }

    @Deprecated
    public boolean isHovering(int left, int top) {
        GLFWWindow.Position<Double> cursorPos = Main.getActiveWindow().getCursorPos();
        int right = left + getWidth(), bottom = top + getHeight();

        return left < cursorPos.getX()
          && cursorPos.getX() < right
          && top < cursorPos.getY()
          && cursorPos.getY() < bottom;
    }
}
