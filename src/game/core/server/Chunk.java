package game.core.server;

import game.mechanics.blocks.Block;
import org.joml.Vector3i;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.function.Supplier;

public class Chunk implements Serializable {
    private final Block[][][] blocks;

    private static Block[][][] createBlocks() {
        Block[][][] out = new Block[16][16][16];
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                for (int z = 0; z < 16; z++) {
                    out[x][y][z] = new Block();
                }
            }
        }
        return out;
    }

    public Chunk() {
        this(createBlocks());
    }

    private Chunk(Block[][][] blocks) {
        this.blocks = blocks;
    }

    public Block getBlock(int x, int y, int z) {
        if (outOfBounds(x, y, z)) throw new IndexOutOfBoundsException();
        return blocks[x][y][z];
    }

    public Block getBlock(Vector3i block) {
        return getBlock(block.x(), block.y(), block.z());
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (outOfBounds(x, y, z)) throw new IndexOutOfBoundsException();
        blocks[x][y][z] = block;
    }

    public boolean outOfBounds(int x, int y, int z) {
        return !(0 <= x && x < 16 && 0 <= y && y < 16 && 0 <= z && z < 16);
    }

    public static Chunk decode(JSONArray input) {
        Block[][][] out = new Block[16][16][16];
        for (int x = 0; x < 16; x++) {
            JSONArray jsonSubArray1 = (JSONArray) input.get(x);
            for (int y = 0; y < 16; y++) {
                JSONArray jsonSubArray2 = (JSONArray) jsonSubArray1.get(y);
                for (int z = 0; z < 16; z++) {
                    out[x][y][z] = Block.decode((JSONObject) jsonSubArray2.get(z));
                }
            }
        }
        return new Chunk(out);
    }

    public static JSONArray encode(Chunk chunk) {
        JSONArray jsonArray = new JSONArray();
        for (int x = 0; x < 16; x++) {
            JSONArray jsonSubArray1 = new JSONArray();
            for (int y = 0; y < 16; y++) {
                JSONArray jsonSubArray2 = new JSONArray();
                for (int z = 0; z < 16; z++) {
                    jsonSubArray2.add(Block.encode(chunk.blocks[x][y][z]));
                }
                jsonSubArray1.add(jsonSubArray2);
            }
            jsonArray.add(jsonSubArray1);
        }
        return jsonArray;
    }
}
