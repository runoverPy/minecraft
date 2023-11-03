package game.assets.mcui.container;

import game.assets.event.MouseEvent;
import game.assets.event.dispatch.InputMap;
import game.assets.mcui.Component;
import game.assets.mcui.PixelComponent;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.PixelImageTile;
import game.main.Main;
import game.util.ImageFile;
import game.window.CursorPosCallback;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

class ScrollBarY extends PixelComponent {
    private Knob knob;
    private ColorTile background;

    private double value;
    private boolean active;

    public ScrollBarY() {
        this.value = 0;

        background = new ColorTile(new Vector4f(0f, 0f, 0f, 0.5f));
        background.setParent(this);
        knob = new Knob();
        knob.setPxHeight(8);
        knob.setParent(this);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void layout() {
        background.setPxSize(getPxWidth(), getPxHeight());
        knob.setPxWidth(getPxWidth());
    }

    @Override
    public void render(Matrix4f matrix) {
        if (!active) return;
        layoutIfScaleChanged();
        background.render(matrix);
        knob.render(matrix);
    }

    @Override
    public Component pick(double x, double y) {
        if (!active) return null;
        Component optionalKnob = knob.pick(x, y);
        if (optionalKnob != null)
            return optionalKnob;
        else
            return super.pick(x, y);
    }

    private class Knob extends PixelComponent {
        private final ColorTile outerTile;
        private final PixelImageTile innerTile;
        private final InputMap<Knob> inputMap;

        private final CursorPosCallback cursorPosCallback = (xPos, yPos) -> {

        };

        private boolean clicked;
        private int clickOffset;

        public Knob() {
            outerTile = new ColorTile(new Vector4f(0, 0, 0, 1));
            outerTile.setParent(this);
            innerTile = new PixelImageTile(ImageFile.loadImage("/img/stone.png"));
            innerTile.setParent(this);

            inputMap = new InputMap<>(this);
            inputMap.addEventMapping(
              new InputMap.MouseMapping(MouseEvent.MOUSE_PRESSED, event -> {
                  System.out.println("Knob pressed");
                  clicked = true;
                  clickOffset = Main.getActiveWindow().getCursorPos().y().intValue() - getGlobalY();
              })
            );
        }

        @Override
        public void layout() {
            outerTile.setPxSize(getPxWidth(), getPxHeight());
            innerTile.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
            innerTile.setLayoutPos(getPxScale(), getPxScale());
        }

        @Override
        public int getLayoutY() {
            return (int) (value * (ScrollBarY.this.getHeight() - getHeight()));
        }

        @Override
        public void render(Matrix4f matrix) {
            if (Main.getActiveWindow().getMouseButton(GLFW_MOUSE_BUTTON_LEFT) != GLFW_PRESS && clicked) {
                clicked = false;
            }

            Vector4f frameColor = clicked || isHovering() ?
              new Vector4f(1, 1, 1, 1) :
              new Vector4f(0, 0, 0, 1);
            outerTile.setBoxColor(frameColor);

            if (clicked) {
                int
                  mouseY = Main.getActiveWindow().getCursorPos().y().intValue(),
                  knobPos = mouseY - clickOffset,
                  totalLength = ScrollBarY.this.getHeight() - getHeight(),
                  top = ScrollBarY.this.getGlobalY(),
                  bottom = top + totalLength,
                  clampedPos = Math.max(top, Math.min(knobPos, bottom)) - top;

                value = (double) clampedPos / totalLength;
            }

            layoutIfScaleChanged();
            outerTile.render(matrix);
            innerTile.render(matrix);
        }
    }
}
