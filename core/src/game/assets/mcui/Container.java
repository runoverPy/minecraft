package game.assets.mcui;

import java.util.ArrayList;
import java.util.List;

public abstract class Container extends Component {
    protected final List<Component> children;

    public Container() {
        this.children = new ArrayList<>();
    }

    public void append(Component child) {
        this.children.add(child);
    }

    public void remove(Component child) {
        this.children.remove(child);
    }
}
