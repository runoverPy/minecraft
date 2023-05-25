package game.assets.mcui.control;

import game.assets.event.MouseEvent;
import game.assets.event.dispatch.InputMap;
import game.assets.mcui.PixelComponent;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.ImageTile;
import game.util.Image;
import game.util.relay.DoubleRelay;
import game.window.CursorPosCallback;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class Slider<T extends Number> extends PixelComponent {
    private Knob knob;
    private DoubleRelay value;

    @Override
    public void render(Matrix4f matrix) {
        layoutIfScaleChanged();
    }

    private class Knob extends PixelComponent {
        private final ColorTile outerTile;
        private final ImageTile innerTile;
        private final InputMap<Knob> inputMap;

        private final CursorPosCallback cursorPosCallback = (xPos, yPos) -> {

        };

        private boolean clicked;
        private int clickOffset;

        public Knob() {
            outerTile = new ColorTile(new Vector4f(0, 0, 0, 1));
            innerTile = new ImageTile(Image.loadImage("/img/stone.png"));
            outerTile.setLayoutPos(0, 0);
            innerTile.setLayoutPos(1, 1);

            inputMap = new InputMap<>(this);
            inputMap.addEventMapping(
                    new InputMap.MouseMapping(MouseEvent.MOUSE_PRESSED, event -> {
                        clicked = true;
                    })
            );
        }

        @Override
        public void layout() {
            outerTile.setPxSize(getAbsWidth(), getAbsHeight());
            innerTile.setPxSize((getAbsWidth() - 2), (getAbsHeight() - 2));
        }

        @Override
        public void render(Matrix4f matrix) {
            layoutIfScaleChanged();
            outerTile.render(matrix);
            innerTile.render(matrix);
        }
    }

}
