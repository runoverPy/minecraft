package game.core.binary.bvt.types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTShortArray extends BVTValue {
    private final short[] values;

    public BVTShortArray(short[] values) {
        this.values = values;
    }

    public short getValueAt(int index) {
        return values[index];
    }

    public void setValueAt(int index, short value) {
        values[index] = value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {

    }

    public static BVTShortArray readValue(DataInputStream stream) throws IOException {
        int size = stream.readInt();
        short[] values = new short[size];
        for (int i = 0; i < size; i++) {
            values[i] = stream.readShort();
        }
        return new BVTShortArray(values);
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
