package game.core.binary.bvt;

import java.io.DataOutputStream;
import java.io.IOException;

public class BVTNull extends BVTValue {
    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x01);
    }

    @Override
    public Object toJSON() {
        return null;
    }

    public static BVTNull readValue() {
        return new BVTNull();
    }
}
