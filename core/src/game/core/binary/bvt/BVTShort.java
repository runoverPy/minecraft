package game.core.binary.bvt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTShort extends BVTValue {
    private final short value;

    public BVTShort(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x09);
        stream.writeShort(value);
    }

    @Override
    public Object toJSON() {
        return null;
    }

    public static BVTShort readValue(DataInputStream stream) throws IOException {
        return new BVTShort(stream.readShort());
    }
}
