package game.core.server.core;

import org.json.simple.JSONObject;

public class JSONPacket {
    static final String descTag = "msg";
    static final String dataTag = "data";


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
