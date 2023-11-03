package game.core.binary.bvt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTLong extends BVTValue {
    private final long value;

    public BVTLong(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x0B);
        stream.writeLong(value);
    }

    @Override
    public Object toJSON() {
        return null;
    }

    public static BVTLong readValue(DataInputStream stream) throws IOException {
        return new BVTLong(stream.readLong());
    }
}
