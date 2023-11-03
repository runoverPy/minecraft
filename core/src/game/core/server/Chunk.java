package game.core.server;

import game.mechanics.Air;
import octree.Octree;
import org.json.JSONArray;
import org.json.JSONObject;

public class Chunk implements mdk.blocks.Chunk<Block> {
    private final Octree<Block> octree;
    private final int cX, cY, cZ;

    public Chunk(int cX, int cY, int cZ) {
        this.cX = cX;
        this.cY = cY;
        this.cZ = cZ;
        this.octree = new Octree<>(4);
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                for (int z = 0; z < 16; z++) {
                    octree.set(x, y, z, new Block(new Air(), "core::air"));
                }
            }
        }
        octree.defragment();
    }

    public Block getBlock(int x, int y, int z) {
        if (outOfBounds(x, y, z)) throw new IndexOutOfBoundsException();
        return octree.get(x, y, z);
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (outOfBounds(x, y, z)) throw new IndexOutOfBoundsException();
        octree.set(x, y, z, block, true);
    }

    public boolean outOfBounds(int x, int y, int z) {
        return !(0 <= x && x < 16 && 0 <= y && y < 16 && 0 <= z && z < 16);
    }

    public static Chunk decode(JSONArray input) {
        Chunk chunk = new Chunk(0, 0, 0);
        for (int x = 0; x < 16; x++) {
            JSONArray jsonSubArray1 = (JSONArray) input.get(x);
            for (int y = 0; y < 16; y++) {
                JSONArray jsonSubArray2 = (JSONArray) jsonSubArray1.get(y);
                for (int z = 0; z < 16; z++) {
                    chunk.octree.set(x, y, z, Block.decode((JSONObject) jsonSubArray2.get(z)));
                }
            }
        }
        chunk.octree.defragment();
        return chunk;
    }

    public static JSONArray encode(Chunk chunk) {
        JSONArray jsonArray = new JSONArray();
        for (int x = 0; x < 16; x++) {
            JSONArray jsonSubArray1 = new JSONArray();
            for (int y = 0; y < 16; y++) {
                JSONArray jsonSubArray2 = new JSONArray();
                for (int z = 0; z < 16; z++) {
                    jsonSubArray2.put(Block.encode(chunk.octree.get(x, y, z)));
                }
                jsonSubArray1.put(jsonSubArray2);
            }
            jsonArray.put(jsonSubArray1);
        }
        return jsonArray;
    }
}
