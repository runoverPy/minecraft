package game.core.server.connect;

import game.core.server.Chunk;
import game.core.server.Client;
import game.core.server.World;
import game.mechanics.blocks.Block;
import game.mechanics.entities.Entity;
import org.joml.Vector3i;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Connection implements Client {
    private final World world;

    public ClientConnection(Socket socket, World world) throws IOException {
        super(socket);
        this.world = world;
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
            case "set_block": {
                JSONObject pos = msgData.getJSONObject("pos");
                int x = pos.getInt("x");
                int y = pos.getInt("y");
                int z = pos.getInt("z");
                JSONObject block = msgData.getJSONObject("block");
                world.setBlock(x, y, z, Block.decode(block));
            }
            default: {
                // erroneous message tag
                System.err.println("Malformed JSON packet:");
                System.err.println(object.toString(2));
            }
        }
    }

    @Override
    public void updateBlock(Vector3i block) {
        updateBlock(block.x(), block.y(), block.z());
    }

    @Override
    public void updateBlock(int x, int y, int z) {
        JSONObject pos = new JSONObject();
        pos.put("x", x);
        pos.put("y", y);
        pos.put("z", z);
        JSONObject data = new JSONObject();
        data.put("pos", pos);
        data.put("block", Block.encode(world.getBlock(x, y, z)));
        JSONObject packet = new JSONObject();
        packet.put("desc", "update_block");
        packet.put("data", data);
        write(packet.toString());
    }

    @Override
    public void userUpdate(Entity.EntityUpdate update) {

    }

    @Override
    public void loadChunk(int cX, int cY, int cZ) {
        world.addChunkRef(cX, cY, cZ);
        JSONObject pos = new JSONObject();
        pos.put("x", cX);
        pos.put("y", cY);
        pos.put("z", cZ);
        JSONArray chunkData = Chunk.encode(world.getChunk(cX, cY, cZ));
        JSONObject data = new JSONObject();
        data.put("chunk_data", chunkData);
        data.put("pos", pos);
        JSONObject packet = new JSONObject();
        packet.put("desc", "load_chunk");
        packet.put("data", data);
        write(packet.toString());
    }

    @Override
    public void dropChunk(int cX, int cY, int cZ) {
        world.subChunkRef(cX, cY, cZ);
        JSONObject pos = new JSONObject();
        pos.put("x", cX);
        pos.put("y", cY);
        pos.put("z", cZ);
        JSONObject data = new JSONObject();
        data.put("pos", pos);
        JSONObject packet = new JSONObject();
        packet.put("desc", "drop_chunk");
        packet.put("data", pos);
        write(packet.toString());
    }

    @Override
    public void close() throws IOException {
        world.disconnectClient(this);
        super.close();
    }
}