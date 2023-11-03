package octree;

import game.assets.event.EventType;
import game.util.DeepCopy;

import java.util.Iterator;

public class OctNode<T extends DeepCopy<T>> extends OctPart<T> {
    private final OctPart<T>[] children; // index bits: 0xZYX

    public OctNode(OctPart<T>[] children, int layer) {
        super(layer);
        if (children.length != 8) throw new IllegalArgumentException("Array Length must be 8");
        this.children = children;
    }

    @Override
    public T get(int x, int y, int z) {
        return children[getIndex(x, y, z)].get(x, y, z);
    }

    @Override
    public OctPart<T> set(int x, int y, int z, T value) {
        int index = getIndex(x, y, z); // a function of x, y, z, layer
        children[index] = children[index].set(x, y, z, value);
        return this;
    }

    @Override
    public OctPart<T> fill(int beginX, int endX, int beginY, int endY, int beginZ, int endZ, T value) {
        return null;
    }

    private void setChildren(OctPart<T>[] newChildren) {
        System.arraycopy(newChildren, 0, this.children, 0, 8);
    }

    @Override
    public OctPart<T> format() {
        for (int i = 0; i < 8; i++) {
            children[i] = children[i].format();
        }
        OctPart<T> test = null;
        for (OctPart<T> child : children) {
            child = child.format();
            if (test == null && child != null) test = child;
            else if (test != null && child != null && !OctPart.fitTogether(test, child))
                return this;
        }
        if (test == null) return null;
        return new OctLeaf<>(((OctLeaf<T>) test).value, layer);
    }

    private int getIndex(int x, int y, int z) {
        if (layer == 0) throw new RuntimeException();
        int
          x0 = x >> (layer - 1) & 1,
          y0 = y >> (layer - 1) & 1,
          z0 = z >> (layer - 1) & 1;
        return x0 | y0 << 1 | z0 << 2;
    }

    @Override
    protected void print(StringBuilder builder, String prefix, String childrenPrefix) {
        builder.append(prefix);
        for (int i = 0; i < 8; i++) {
            OctPart<T> next = children[i];
            if (i == 0)
                next.print(builder, "┬──" + (next instanceof OctNode<T> ? "─" : " "), childrenPrefix + "│   ");
            else if (i == 7)
                next.print(builder, childrenPrefix + "└──" + (next instanceof OctNode<T> ? "─" : " "), childrenPrefix + "    ");
            else
                next.print(builder, childrenPrefix + "├──" + (next instanceof OctNode<T> ? "─" : " "), childrenPrefix + "│   ");
        }
    }

    @Override
    public int size() {
        int size = 0;
        for (OctPart<T> child : children) {
            size += child.size();
        }
        return size;
    }
}
