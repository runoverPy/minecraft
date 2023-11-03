package octree;

import game.assets.event.EventType;
import game.util.DeepCopy;

import java.util.Iterator;

public class OctLeaf<T extends DeepCopy<T>> extends OctPart<T> {
    protected T value;

    public OctLeaf(T value, int layer) {
        super(layer);
        this.value = value;
    }

    @Override
    public T get(int x, int y, int z) {
        return value;
    }

    @Override
    public OctPart<T> set(int x, int y, int z, T value) {
        if (layer == 0) {
            this.value = value;
            return this;
        } else {
            return fragment().set(x, y, z, value);
        }
    }

    @Override
    public OctPart<T> fill(int beginX, int endX, int beginY, int endY, int beginZ, int endZ, T value) {
        return null;
    }

    @Override
    public OctPart<T> format() {
        return this;
    }

    private OctNode<T> fragment() {
        OctPart<T>[] values = new OctPart[8];
        for (int i = 0; i < 8; i++) {
            values[i] = new OctLeaf<>(value != null ? value.deepcopy() : null, layer - 1);
        }
        return new OctNode<>(values, layer);
    }

    @Override
    protected void print(StringBuilder builder, String prefix, String childrenPrefix) {
        builder.append(prefix)
          .append(value != null ? value.toString() : "null")
          .append('\n');
    }

    @Override
    public int size() {
        return 1;
    }
}
