package game.core.binary.bvt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class BVTValue {
    public abstract void writeValue(DataOutputStream stream) throws IOException;

    public abstract Object toJSON();

    public static BVTValue readValue(DataInputStream stream) throws IOException {
        class BVTFormatException extends IOException {}
        return switch (stream.readByte()) {
            case 0x01 -> BVTNull.readValue();
            case 0x02 -> BVTBool.readValue(false);
            case 0x03 -> BVTBool.readValue(true);
            case 0x06 -> BVTTuple.readValue(stream);
            case 0x07 -> BVTObject.readValue(stream);
            case 0x08 -> BVTByte.readValue(stream);
            case 0x09 -> BVTShort.readValue(stream);
            case 0x0A -> BVTInt.readValue(stream);
            case 0x0B -> BVTLong.readValue(stream);
            case 0x0D -> BVTString.readValue(stream);
            case 0x0E -> BVTFloat.readValue(stream);
            case 0x0F -> BVTDouble.readValue(stream);
            default -> throw new BVTFormatException();
        };
    }
}
