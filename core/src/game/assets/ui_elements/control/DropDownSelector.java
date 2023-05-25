package game.assets.ui_elements.control;

import game.assets.ui_elements.ChildBox;
import game.assets.ui_elements.Component;
import game.util.relay.ObjectRelay;
import org.joml.Matrix4f;

import java.util.List;

public class DropDownSelector<E> extends ChildBox {
    private final ObjectRelay<E> buffer;

    private final List<E> options;

    private boolean opened;

    public DropDownSelector(int width, int height, int xOffset, int yOffset, Component parent, String name, ObjectRelay<E> buffer, List<E> options) {
        super(width, height, xOffset, yOffset, parent);
        this.buffer = buffer;
        this.options = options;
    }

    @Override
    public void draw(int pxScale, Matrix4f matrix4f) {

    }

    public void includeOption(E option) {}

    public void includeOptions(List<E> options) {}
}
