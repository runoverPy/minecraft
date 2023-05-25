package game.assets.mcui;

import game.assets.mcui.data.ListChangeListener;
import game.assets.mcui.data.ObservableList;
import game.assets.mcui.data.ObservableListWrapper;

import java.util.ArrayList;

public abstract class Parent extends Component {
    private final ObservableList<Component> children;

    public Parent() {
        children = new ObservableListWrapper<>(new ArrayList<>());
        children.addListener(change -> {
            if (change.getType() == ListChangeListener.ChangeType.REMOVE) {
                if (change.getValue().getParent() == null)
                    throw new IllegalStateException("Component parent was null before removing");
                change.getValue().setParent(null);
            }
            if (change.getType() == ListChangeListener.ChangeType.INSERT) {
                if (change.getValue().getParent() != null)
                    throw new IllegalStateException("Component parent may not be assigned twice");
                change.getValue().setParent(this);
                change.getValue().layout();
            }
            layout();
        });
    }

    public ObservableList<Component> getChildren() {
        return children;
    }
}
