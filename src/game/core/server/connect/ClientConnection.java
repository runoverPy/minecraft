package game.core.server.connect;

import game.core.server.core.Client;
import game.core.server.core.Server;
import org.joml.Vector3i;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Connection implements Client {
    private final Server server;

    public ClientConnection(Socket socket, Server server) throws IOException {
        super(socket);
        this.server = server;
    }

    @Override
    protected void receivePacket(String packet) {
        JSONObject object;
        try {
            object = (JSONObject) new JSONParser().parse(packet);
        } catch (ParseException e) {
            return;
        }
        String msgType = (String) object.get("msg");
        switch (msgType) {
            case "getBlock": {}
            case "setBlock": {}
            case "updateBlock": {}
            case "getSpawnPoint": {}
            default: {
                // erroneous message tag
            }
        }
    }

    @Override
    public void updateBlock(Vector3i block) {

    }

    @Override
    public void updateBlock(int x, int y, int z) {

    }

    @Override
    public void loadChunk(int cX, int cY, int cZ) {

    }

    @Override
    public void close() throws IOException {
        server.disconnectClient(this);
        super.close();
    }
}