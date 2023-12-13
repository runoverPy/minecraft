package game.core.binary.bvt.types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTIntArray extends BVTValue {
    private final int[] values;

    public BVTIntArray(int[] values) {
        this.values = values;
    }

    public int getValueAt(int index) {
        return values[index];
    }

    public void setValueAt(int index, int value) {
        values[index] = value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {

    }

    public static BVTIntArray readValue(DataInputStream stream) throws IOException {
        int size = stream.readInt();
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = stream.readInt();
        }
        return new BVTIntArray(values);
    }

    @Override
    public Object toJSON() {
        return null;
    }

    @Override
    public String prettyPrint(int indent) {
        StringBuilder builder = new StringBuilder();
        if (values.length != 0) {
            indent++;
            builder.append("[\n");
            for (int i = 0; i < values.length; i++) {
                builder
                  .append("    ".repeat(indent))
                  .append(values[i]);
                if (i != values.length - 1) {
                    builder.append(",");
                }
                builder.append("\n");
            }
            builder
              .append("    ".repeat(indent - 1))
              .append("]");
        } else {
            builder.append("[ ]");
        }
        return builder.toString();
    }
}
