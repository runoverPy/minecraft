package octree;

import game.util.DeepCopy;

public class Octree<T extends DeepCopy<T>> {
    private OctPart<T> root;
    private int depth;
    private int prevSets;

    public void raise() {
        depth++;
    }

    public Octree(int depth) {
        this.depth = depth;
        this.root = new OctLeaf<>(null, depth);
    }

    public T get(int x, int y, int z) {
        checkBounds(x, y, z);
        return root.get(x, y, z);
    }

    public void set(int x, int y, int z, T value, boolean autoFormat) {
        if (value == null) throw new NullPointerException("Value may not be null");
        checkBounds(x, y, z);
        root = root.set(x, y, z, value);
        prevSets++;
        if (autoFormat && prevSets < (int) Math.pow(8, depth - 2)) {
            defragment();
            prevSets = 0;
        }
    }

    public void set(int x, int y, int z, T value) {
        set(x, y, z, value, false);
    }

    /**
     *
     * @return maximum number of layers for this octree
     */
    public int getDepth() {
        return depth;
    }

    public int getWidth() {
        return (int) Math.pow(2, getDepth());
    }

    public int capacity() {
        return (int) Math.pow(getWidth(), 3);
    }

    public int size() {
        return root.size();
    }

    private void checkBounds(int x, int y, int z) {
        if (x < 0 || getWidth() <= x || y < 0 || getWidth() <= y || z < 0 || getWidth() <= z)
            throw new IndexOutOfBoundsException(String.format("Indexes must be within half-open range [0, %d), but are {x: %d, y: %d, z: %d}", getWidth(), x, y, z));
    }

    public void defragment() {
        root = root.format();
    }

    /**
     * @apiNote the actual value given to the function will not be stored in the octree, it will instead be cloned
     * @param beginX
     * @param endX
     * @param beginY
     * @param endY
     * @param beginZ
     * @param endZ
     * @param value
     */
    public void fill(int beginX, int endX, int beginY, int endY, int beginZ, int endZ, T value) {}

    public static void main(String[] args) {
        class Data implements DeepCopy<Data> {
            String value;

            public Data(String value) {
                this.value = value;
            }

            public Data() {
                this(null);
            }

            @Override
            public boolean equals(Object obj) {
                return obj instanceof Data && ((Data) obj).value.equals(value);
            }

            public String toString() { return value; }

            @Override
            public Data copy() {
                return new Data(this.value);
            }
        }

        Octree<Data> tree = new Octree<>(3);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                for (int k = 0; k < 8; k++)
                    tree.set(i, j, k, new Data("Hello World"));

        tree.set(1, 2, 3, new Data("Radical, Dude!"));
        tree.defragment();
        System.out.println(tree);
        System.out.println(tree.size() + " / " + tree.capacity());
    }

    @Override
    public String toString() {
        return root == null ? "null" : root.print();
    }
}