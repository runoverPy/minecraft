package game.core.binary.bvt.types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class BVTValue {
    public abstract void writeValue(DataOutputStream stream) throws IOException;

    public abstract Object toJSON();

    public static BVTValue readValue(DataInputStream stream) throws IOException {
        class BVTFormatException extends IOException {}
        byte tag = stream.readByte();
        return switch (tag) {
            case 0x01 -> BVTNull.readValue();
            case 0x02 -> BVTBool.readValue(false);
            case 0x03 -> BVTBool.readValue(true);
            case 0x15 -> BVTBoolArray.readValue(stream);
            case 0x25 -> BVTFloatArray.readValue(stream);
            case 0x35 -> BVTDoubleArray.readValue(stream);
            case 0x45 -> BVTByteArray.readValue(stream);
            case 0x55 -> BVTShortArray.readValue(stream);
            case 0x65 -> BVTIntArray.readValue(stream);
            case 0x75 -> BVTLongArray.readValue(stream);
            case 0x06 -> BVTTuple.readValue(stream);
            case 0x07 -> BVTObject.readValue(stream);
            case 0x08 -> BVTByte.readValue(stream);
            case 0x09 -> BVTShort.readValue(stream);
            case 0x0A -> BVTInt.readValue(stream);
            case 0x0B -> BVTLong.readValue(stream);
            case 0x0C -> BVTChar.readValue(stream);
            case 0x0D -> BVTString.readValue(stream);
            case 0x0E -> BVTFloat.readValue(stream);
            case 0x0F -> BVTDouble.readValue(stream);
            default -> throw new BVTFormatException();
        };
    }

    public abstract String prettyPrint(int indent);

    public static BVTValue wrapNumber(Number number) {
        if (number instanceof Byte byteNumber) return new BVTByte(byteNumber);
        if (number instanceof Short shortNumber) return new BVTShort(shortNumber);
        if (number instanceof Integer intNumber) return new BVTInt(intNumber);
        if (number instanceof Long longNumber) return new BVTLong(longNumber);
        if (number instanceof Float floatNumber) return new BVTFloat(floatNumber);
        if (number instanceof Double doubleNumber) return new BVTDouble(doubleNumber);
        if (number instanceof BigInteger bigIntegerNumber) {
            return new BVTInt(bigIntegerNumber.intValue());
        }
        if (number instanceof BigDecimal bigDecimalNumber) {
            return new BVTFloat(bigDecimalNumber.floatValue());
        }
        return null;
    }

    public abstract static class Serializer<T extends BVTValue> {
        public abstract T readValue(DataInputStream stream);
        public abstract void writeValue(DataOutputStream stream);
    }
}
