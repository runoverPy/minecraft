package game.core.binary.bvt;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
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
}
