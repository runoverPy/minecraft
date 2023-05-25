package game.assets.mcui.data;

import java.util.Set;

public interface ObservableSet<T> extends Observable<SetChangeListener<T>>, Set<T> {

}
