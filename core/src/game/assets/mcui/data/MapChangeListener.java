package game.assets.mcui.data;

public interface MapChangeListener<K, V> extends ChangeListener<MapChangeListener.MapChange<K, V>> {
    class MapChange<K, V> extends Change<ObservableMap<K, V>> {
        public MapChange(ObservableMap<K, V> observable) {
            super(observable);
        }
    }
}
