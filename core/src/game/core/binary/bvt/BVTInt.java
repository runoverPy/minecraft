package game.core.binary.bvt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTInt extends BVTValue {
    private final int value;

    public BVTInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x0A);
        stream.writeInt(value);
    }

    @Override
    public Object toJSON() {
        return null;
    }

    public static BVTInt readValue(DataInputStream stream) throws IOException {
        return new BVTInt(stream.readInt());
    }
}
