package octree;

import game.assets.event.EventType;
import game.util.DeepCopy;

import java.util.Iterator;
import java.util.Objects;

public abstract class OctPart<T extends DeepCopy<T>> {
    protected final int layer;

    public OctPart(int layer) {
        if (layer < 0) throw new IllegalArgumentException("Layer must not be less than 0");
        this.layer = layer;
    }

    protected int getLayer() {
        return layer;
    }

    public abstract T get(int x, int y, int z);

    public abstract OctPart<T> set(int x, int y, int z, T value);

    public abstract OctPart<T> fill(int beginX, int endX, int beginY, int endY, int beginZ, int endZ, T value);

    public abstract OctPart<T> format();

    protected static <T extends DeepCopy<T>> boolean fitTogether(OctPart<T> a, OctPart<T> b) {
        if (a instanceof OctNode<T> || b instanceof OctNode<T>) return false;
        return a.layer == b.layer && Objects.equals(((OctLeaf<T>) a).value, ((OctLeaf<T>) b).value);
    }

    protected abstract void print(StringBuilder builder, String prefix, String childrenPrefix);

    public String print() {
        StringBuilder builder = new StringBuilder();
        print(builder, "", "");
        return builder.toString();
    }

    public abstract int size();
}
