package game.assets.ui_elements;

import game.util.relay.ObjectRelay;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class DropDownSelector<E> extends ChildBox {
    private final ObjectRelay<E> buffer;

    private final List<E> options;

    public DropDownSelector(int width, int height, int xOffset, int yOffset, Component parent, ObjectRelay<E> buffer) {
        super(width, height, xOffset, yOffset, parent);
        this.buffer = buffer;
        this.options = new ArrayList<>();
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {

    }
}
