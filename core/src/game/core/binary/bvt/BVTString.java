package game.core.binary.bvt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BVTString extends BVTValue {
    private final String value;

    public BVTString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x0D);
        byte[] string = value.getBytes(StandardCharsets.UTF_8);
        stream.writeShort(string.length);
        stream.write(string);
    }

    @Override
    public String toJSON() {
        return value;
    }

    public static BVTString readValue(DataInputStream stream) throws IOException {
        short length = stream.readShort();
        byte[] string = stream.readNBytes(length);
        return new BVTString(new String(string));
    }
}
