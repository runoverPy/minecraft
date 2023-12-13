package game.core.binary.bvt.types;

public class MultiArray<T> {
    private final int dimensions;
    private TreeNode rootNode;

    public MultiArray(int dimensions) {
        this.dimensions = dimensions;
    }

    T get(int[] index) {
        return null;
    }

    MultiArray<T> getArray(int[] index) {
        return null;
    }

    void set(int[] index, T value) {

    }

    void setArray(int[] index, MultiArray<T> value) {

    }

    private class TreeNode {
        T get(int[] index, int depth) {
            return null;
        }

        void set(int[] index, int depth, T value) {

        }
    }

    private class ArrayNode extends TreeNode {
        TreeNode[] array;
    }

    private class ValueNode extends TreeNode {
        T value;
    }
}
