package game.assets.mcui.container;

import game.assets.mcui.Component;
import game.assets.mcui.Parent;
import game.assets.mcui.PixelComponent;
import org.joml.Matrix4f;

public abstract class Container extends Parent {
    private final PixelComponent scaleReminder = new PixelComponent() {
        @Override
        public void layout() {
            Container.this.layout();
        }
    };

    @Override
    public final Component pick(double x, double y) {
        Component selected = null;
        for (Component child : getChildren()) {
            Component pickedChild = child.pick(x, y);
            if (pickedChild != null)
                selected = pickedChild;
        }
        if (selected != null)
            return selected;
        if (contains(x, y))
            return this;
        else
            return null;
    }

    @Override
    public void render(Matrix4f matrix) {
        scaleReminder.layoutIfScaleChanged();
        getChildren().forEach(component -> component.render(matrix));
    }

    @Override
    public abstract void layout();
}
