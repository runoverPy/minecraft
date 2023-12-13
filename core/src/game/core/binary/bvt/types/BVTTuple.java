package game.core.binary.bvt.types;

import org.json.JSONArray;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BVTTuple extends BVTValue {
    private final List<BVTValue> values;

    public BVTTuple() {
        values = new ArrayList<>();
    }

    public List<BVTValue> getValues() {
        return values;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x06);
        stream.writeShort(values.size());
        for (BVTValue value : values) {
            value.writeValue(stream);
        }
    }

    @Override
    public JSONArray toJSON() {
        JSONArray array = new JSONArray();
        for (BVTValue value : values)
            array.put(value.toJSON());
        return array;
    }

    public static BVTTuple readValue(DataInputStream stream) throws IOException {
        short length = stream.readShort();
        BVTTuple array = new BVTTuple();
        for (int i = 0; i < length; i++) {
            BVTValue value = BVTValue.readValue(stream);
            array.values.add(value);
        }
        return array;
    }

    @Override
    public String prettyPrint(int indent) {
        StringBuilder builder = new StringBuilder();
        if (!values.isEmpty()) {
            indent++;
            builder.append("[\n");
            for (int i = 0; i < values.size(); i++) {
                builder
                  .append("    ".repeat(indent))
                  .append(values.get(i) == null ? "null" : values.get(i).prettyPrint(indent));
                if (i != values.size() - 1) {
                    builder.append(",");
                }
                builder.append("\n");
            }
            builder
              .append("    ".repeat(indent - 1))
              .append("]");
        } else {
            builder.append("[ ]");
        }
        return builder.toString();
    }
}
