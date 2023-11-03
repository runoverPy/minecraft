package game.core.binary.bvt;

import java.io.DataOutputStream;
import java.io.IOException;

public class BVTBool extends BVTValue {
    private final boolean value;

    public BVTBool(Boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Boolean toJSON() {
        return value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(value ? 0x03 : 0x02);
    }

    public static BVTBool readValue(boolean value) {
        return new BVTBool(value);
    }
}
