package game.core.binary.bvt.types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BVTBoolArray extends BVTValue {
    private final int[] values;

    protected BVTBoolArray(int[] values) {
        this.values = values;
    }

    public static BVTBoolArray fromBinArray(String binArray) {
        StringReader reader = new StringReader(binArray);
        List<Integer> accumulator = new ArrayList<>();
        int value = 0, count = 0;
        try {
            while (reader.ready()) {
                int next = reader.read();
                if (next == -1) break;
                if (next == '0') {
                    count++;
                } else if (next == '1') {
                    value |= 1 << 31 - count;
                    count++;
                }
                if (count == 32) {
                    accumulator.add(value);
                    value = count = 0;
                }
            }
            if (count != 0)
                accumulator.add(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new BVTBoolArray(accumulator.stream().mapToInt(Integer::intValue).toArray());
    }

    public static BVTBoolArray fromHexArray(String hexArray) {
        StringReader reader = new StringReader(hexArray);
        List<Integer> accumulator = new ArrayList<>();
        int value = 0, count = 0;
        try {
            while (reader.ready()) {
                int next = reader.read();
                if (next == -1) break;
                int nextValue = switch (next) {
                    case '0' -> 0;
                    case '1' -> 1;
                    case '2' -> 2;
                    case '3' -> 3;
                    case '4' -> 4;
                    case '5' -> 5;
                    case '6' -> 6;
                    case '7' -> 7;
                    case '8' -> 8;
                    case '9' -> 9;
                    case 'a', 'A' -> 10;
                    case 'b', 'B' -> 11;
                    case 'c', 'C' -> 12;
                    case 'd', 'D' -> 13;
                    case 'e', 'E' -> 14;
                    case 'f', 'F' -> 15;
                    case ']' -> -2;
                    default -> -1;
                };
                if (nextValue == -1) continue;
                if (nextValue == -2) break;
                value |= nextValue << 28 - 4 * count;
                count++;
                if (count == 8) {
                    accumulator.add(value);
                    value = count = 0;
                }
            }
            if (count != 0)
                accumulator.add(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new BVTBoolArray(accumulator.stream().mapToInt(Integer::intValue).toArray());
    }

    public boolean getValueAt(int index) {
        if (values.length <= index / 32) throw new IndexOutOfBoundsException();
        return (values[index / 32] & (1 << (31 - index % 32))) != 0;
    }

    public void setValueAt(int index, boolean value) {
        if (values.length <= index / 32) throw new IndexOutOfBoundsException();
        if (value)
            values[index / 32] |= 1 << (31 - index % 32);
        else
            values[index / 32] &= ~(1 << (31 - index % 32));
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {

    }

    public static BVTBoolArray readValue(DataInputStream stream) throws IOException {
        int size = stream.readInt() / 32;
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = stream.readInt();
        }
        return new BVTBoolArray(values);
    }

    @Override
    public Object toJSON() {
        return null;
    }

    @Override
    public String prettyPrint(int indent) {
        StringBuilder builder = new StringBuilder();
        Function<Integer, String> toString = values.length <= 1 ? Integer::toBinaryString : Integer::toHexString;
        if (values.length != 0) {
            builder.append("[");
            for (int value : values) {
                builder
                  .append(toString.apply(value));
            }
            builder.append("]");
        } else {
            builder.append("[]");
        }
        return builder.toString();
    }
}
