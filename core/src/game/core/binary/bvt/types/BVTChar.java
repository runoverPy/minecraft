package game.core.binary.bvt.types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.nio.charset.StandardCharsets;

public class BVTChar extends BVTValue {
    private final int value;

    public BVTChar(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public char[] getUTF16Value() {
        return Character.toChars(value);
    }

    public byte[] getUTF8Value() {
        return Character.toString(value).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
    }

    public static BVTChar readValue(DataInputStream stream) throws IOException {
        byte head = stream.readByte();
        if (head >= 0) return new BVTChar(head);
        else {
            byte[] values = new byte[4];
            values[0] = head;
            if ((byte) 0b11000000 <= head && head <= (byte) 0b11011111) {
                stream.read(values, 1, 1);
            } else if ((byte) 0b11100000 <= head && head <= (byte) 0b11101111) {
                stream.read(values, 1, 2);
            } else if ((byte) 0b11110000 <= head && head <= (byte) 0b11110111) {
                stream.read(values, 1, 3);
            } else throw new UTFDataFormatException();
            return new BVTChar(new String(values, StandardCharsets.UTF_8).codePointAt(0));
        }
    }

    @Override
    public Object toJSON() {
        return null;
    }

    @Override
    public String prettyPrint(int indent) {
        return Character.toString(value);
    }
}
