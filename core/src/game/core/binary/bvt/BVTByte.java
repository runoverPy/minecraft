package game.core.binary.bvt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTByte extends BVTValue {
    private final byte value;

    public BVTByte(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x08);
        stream.writeByte(value);
    }

    @Override
    public Byte toJSON() {
        return value;
    }

    public static BVTByte readValue(DataInputStream stream) throws IOException {
        return new BVTByte(stream.readByte());
    }
}
