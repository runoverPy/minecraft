package game.core.server.connect;

import org.json.simple.JSONObject;

public class JSONPacket {
    private final String jsonString;
    public JSONPacket() {
        jsonString = "";
    }
    public JSONPacket(String jsonString) {
        this.jsonString = jsonString;
    }

    public static String awake() {
        JSONObject object = new JSONObject();
        object.put("msg", "AWAKE");
        return object.toJSONString();
    }
}
