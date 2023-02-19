package game.core.server.connect;

import game.core.server.Chunk;
import game.core.server.Client;
import game.core.server.Server;
import game.mechanics.blocks.Block;
import game.mechanics.entities.Entity;
import game.mechanics.entities.User;
import org.joml.Vector3f;
import org.joml.Vector3i;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerConnection extends Connection implements Server {
    private final Map<Vector3i, Chunk> chunks;
    private Client client = null;
    private Vector3f spawnPoint = new Vector3f(0, 0, 1);
    private long serverTime;

    public ServerConnection(String addr, int port) throws IOException {
        super(new Socket(addr, port));
        chunks = new HashMap<>();
    }

    @Override
    protected void receivePacket(String packet) {
        JSONObject object;
        try {
            object = new JSONObject(packet);
        } catch (JSONException e) {
            e.printStackTrace(System.err);
            System.err.println("Malformed JSON packet:");
            System.err.println(packet);
            return;
        }

        String msgType = object.getString("desc");
        JSONObject msgData = object.getJSONObject("data");
        switch (msgType) {
            case "update_block": {
                JSONObject pos = msgData.getJSONObject("pos");
                int x = pos.getInt("x");
                int y = pos.getInt("y");
                int z = pos.getInt("z");
                JSONObject block = msgData.getJSONObject("block");
                getChunk(Math.floorDiv(x, 16), Math.floorDiv(y, 16), Math.floorDiv(z, 16))
                        .setBlock(Math.floorMod(x, 16), Math.floorMod(y, 16), Math.floorMod(z, 16), Block.decode(block));
                this.client.updateBlock(x, y, z);
            }
            case "load_chunk": {
                JSONObject pos = msgData.getJSONObject("pos");
                int cX = pos.getInt("x");
                int cY = pos.getInt("y");
                int cZ = pos.getInt("z");
                JSONArray chunkData = msgData.getJSONArray("chunk_data");
                chunks.put(new Vector3i(cX, cY, cZ), Chunk.decode(chunkData));
                client.loadChunk(cX, cY, cZ);
            }
            case "drop_chunk": {
                JSONObject pos = msgData.getJSONObject("pos");
                int x = pos.getInt("x");
                int y = pos.getInt("y");
                int z = pos.getInt("z");
                this.client.updateBlock(x, y, z);
            }
            case "update": {
            }
            default: {
                // erroneous message tag
                System.err.println("Malformed JSON packet:");
                System.err.println(object.toString(2));
            }
        }
    }

    @Override
    public long getServerTime() {
        return serverTime;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return getChunk(Math.floorDiv(x, 16), Math.floorDiv(y, 16), Math.floorDiv(z, 16))
                .getBlock(Math.floorMod(x, 16), Math.floorMod(y, 16), Math.floorMod(z, 16));
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        JSONObject pos = new JSONObject();
        pos.put("x", x);
        pos.put("y", y);
        pos.put("z", z);
        JSONObject data = new JSONObject();
        data.put("pos", pos);
        data.put("block", Block.encode(block));
        JSONObject packet = new JSONObject();
        packet.put("desc", "set_block");
        packet.put("data", data);
        write(packet.toString());
    }

    @Override
    public void registerEntity(Entity entity) {
    }

    @Override
    public void removeEntity(Entity entity) {

    }

    @Override
    public Vector3f getSpawnPoint() {
        return this.spawnPoint;
    }

    @Override
    public void userUpdate(User.UserStatus status) {

    }

    @Override
    public void registerClient(Client client) {
        if (this.client == null) this.client = client;
        else if (this.client != client) throw new RuntimeException("Cannot assign a second client to a connection");
    }

    @Override
    public void disconnectClient(Client client) throws IOException {
        // essentially closes the connection
        if (this.client == client) {
            close();
            this.client = null;
        }
    }

    @Override
    public Chunk getChunk(int cX, int cY, int cZ) {
        Chunk c = chunks.get(new Vector3i(cX, cY, cZ));
        if (c == null) {
            c = new Chunk();
            System.out.println(this + String.format("Creating new chunk for %d, %d, %d", cX, cY, cZ));
            chunks.put(new Vector3i(cX, cY, cZ), c);
        }
        return c;
    }
}
