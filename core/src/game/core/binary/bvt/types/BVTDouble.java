package game.core.binary.bvt.types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTDouble extends BVTValue {
    private final double value;

    public BVTDouble(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x0F);
        stream.writeDouble(value);
    }

    @Override
    public Double toJSON() {
        return value;
    }

    public static BVTDouble readValue(DataInputStream stream) throws IOException {
        return new BVTDouble(stream.readDouble());
    }

    @Override
    public String prettyPrint(int indent) {
        return Double.toString(value);
    }
}
