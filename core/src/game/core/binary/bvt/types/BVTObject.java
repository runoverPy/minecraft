package game.core.binary.bvt.types;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BVTObject extends BVTValue {
    private final Map<String, BVTValue> values;

    public BVTObject() {
        values = new HashMap<>();
    }

    public Map<String, BVTValue> getValues() {
        return values;
    }

    @Override
    public void writeValue(DataOutputStream stream) throws IOException {
        stream.writeByte(0x07);
        stream.writeShort(values.size());
        for (Map.Entry<String, BVTValue> field : values.entrySet()) {
            byte[] name = field.getKey().getBytes();
            stream.writeShort(name.length);
            stream.write(name);
            field.getValue().writeValue(stream);
        }
    }

    @Override
    public Object toJSON() {
        JSONObject object = new JSONObject();
        for (Map.Entry<String, BVTValue> entry : values.entrySet()) {
            object.put(entry.getKey(), entry.getValue().toJSON());
        }
        return object;
    }

    public static BVTObject readValue(DataInputStream stream) throws IOException {
        short length = stream.readShort();
        BVTObject object = new BVTObject();
        for (int i = 0; i < length; i++){
            short stringLength = stream.readShort();
            byte[] string = stream.readNBytes(stringLength);
            String name = new String(string);
            BVTValue value = BVTValue.readValue(stream);
            object.getValues().put(name, value);
        }
        return object;
    }

    @Override
    public String prettyPrint(int indent) {
        StringBuilder builder = new StringBuilder();
        if (!values.isEmpty()) {
            indent++;
            builder.append("{\n");
            for (Iterator<Map.Entry<String, BVTValue>> iter = values.entrySet().iterator(); iter.hasNext();) {
                Map.Entry<String, BVTValue> entry = iter.next();
                builder
                  .append("    ".repeat(indent))
                  .append(entry.getKey())
                  .append(": ")
                  .append(entry.getValue() == null ? "null" : entry.getValue().prettyPrint(indent));
                if (iter.hasNext()) {
                    builder.append(",");
                }
                builder.append("\n");
            }
            builder
              .append("    ".repeat(indent - 1))
              .append("}");
        } else {
            builder.append("{ }");
        }
        return builder.toString();
    }
}
