package game.core.binary.bvt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BVTFloat extends BVTValue {
    private final float value;

    public BVTFloat(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x0E);
        stream.writeFloat(value);
    }

    @Override
    public Float toJSON() {
        return value;
    }

    public static BVTFloat readValue(DataInputStream stream) throws IOException {
        return new BVTFloat(stream.readFloat());
    }
}
