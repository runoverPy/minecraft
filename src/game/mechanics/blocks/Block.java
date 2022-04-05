package game.mechanics.blocks;

import game.core.GameRuntime;
import game.core.modding.BlockBase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Block {
    private final BlockBase innerBlock;
    private final String blockId;

    public Block(String blockId) {
        this.innerBlock = GameRuntime.getInstance().getBlockRegister().createBlockByName(blockId);
        this.blockId = blockId;
    }

    public Block(BlockBase innerBlock, String id) {
        this.innerBlock = innerBlock;
        this.blockId = id;
    }

    public Block() {
        this(new Air(), "core::air");
    }

    public Phase getPhase() {
        return innerBlock.getPhase();
    }

    public String getID() {
        return blockId;
    }

    public Material getMaterial() {
        return innerBlock.getMaterial();
    }

    public void setMetadata(String name, Metadata metadata) {

    }

    public Metadata getMetadata(String name) {
        return null;
    }

    public static JSONObject encode(Block block) {
        JSONObject out = new JSONObject();
        out.put("blockID", block.getID());
        out.put("metadata", new JSONArray());
        return out;
    }

    public static Block decode(JSONObject input) {
        /**
         * "block": {
         *     "blockID": $(blockName),
         *     "metadata: []
         * }
         */
        String blockID = (String) input.get("blockID");
        String metadata;
        return new Block(blockID);
    }

}
