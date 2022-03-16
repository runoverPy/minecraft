package game.core.server.connect;

public class JSONPacket {
    private final String jsonString;
    public JSONPacket() {
        jsonString = "";
    }
    public JSONPacket(String jsonString) {
        this.jsonString = jsonString;
    }
}
