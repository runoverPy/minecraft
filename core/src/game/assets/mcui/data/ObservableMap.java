package game.assets.mcui.data;

import java.util.Map;

public interface ObservableMap<K, V> extends Observable<MapChangeListener<K, V>>, Map<K, V> {
}
