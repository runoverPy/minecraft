package game.core.console.userfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridDirectory extends HybridNode {
    private final HybridStorageVolume volume;
    private final Map<String, HybridNode> childNodes;

    public HybridDirectory(HybridStorageVolume volume) {
        this.volume = volume;
        this.childNodes = new HashMap<>();
    }

    public HybridNode getContentFromNames(List<String> names) {
        if (names.isEmpty()) return this;
        String name = names.remove(0);
        if (!childNodes.containsKey(name)) return null;
        HybridNode node = childNodes.get(name);
        if (node instanceof HybridDirectory dir)
            return dir.getContentFromNames(names);
        return node;
    }

    public void newFile() {}
}
