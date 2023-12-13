package game.core.binary.bvt.types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTDoubleArray extends BVTValue {
    private final double[] values;

    public BVTDoubleArray(double[] values) {
        this.values = values;
    }

    public double getValueAt(int index) {
        return values[index];
    }

    public void setValueAt(int index, double value) {
        values[index] = value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {

    }

    public static BVTDoubleArray readValue(DataInputStream stream) throws IOException {
        int size = stream.readInt();
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = stream.readDouble();
        }
        return new BVTDoubleArray(values);
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
