package game.core.server;

import game.core.GameRuntime;
import game.util.DeepCopy;
import mdk.blocks.Metadata;
import game.mechanics.Air;
import mdk.blocks.BlockBase;
import org.json.JSONArray;
import org.json.JSONObject;
import mdk.blocks.Phase;
import mdk.blocks.Material;

public class Block implements mdk.blocks.Block, DeepCopy<Block> {
    private final BlockBase innerBlock;
    private final String blockId;

    public Block(String blockId) {
        this.innerBlock = GameRuntime.getInstance().getBlockRegister().createBlockBaseByName(blockId);
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

    @Override
    public Block copy() {
        return new Block(blockId);
    }
}
