package game.core.loading;

import game.core.server.Block;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import mdk.blocks.BlockLoader;
import mdk.blocks.BlockBase;

/*
Block name syntax: mod::block.module.hierarchy
 */
public class BlockRegister implements BlockLoader {
    private final Map<String, Supplier<? extends BlockBase>> registeredBlocks;

    public BlockRegister() {
        registeredBlocks = new HashMap<>();
    }

    public JSONObject encodeBlock(Block block) {
        JSONObject out = new JSONObject();
        out.put("blockID", block.getID());
        out.put("metadata", new JSONArray());
        return out;
    }

    public Block decodeBlock(JSONObject input) {
        String blockID = (String) input.get("blockID");
        return new Block(blockID);
    }

    public BlockLoader register(String registryName, Supplier<? extends BlockBase> toRegister) {
        if (registeredBlocks.containsKey(registryName)) throw new RegistrationException("Block already registered");
        else registeredBlocks.put(registryName, toRegister);
        return this;
    }

    public BlockBase createBlockBaseByName(String blockName) {
        if (registeredBlocks.containsKey(blockName)) {
            return registeredBlocks.get(blockName).get();
        }
        else throw new RegistrationException("Block not registered");
    }

    public boolean registered(String blockName) {
        return registeredBlocks.containsKey(blockName);
    }

    public final boolean testJSONConversion(Block block) {
        return block.equals(decodeBlock(encodeBlock(block)));
    }

    public int getAlias(String name) {
        return 0;
    }
    public String getAlias(int id) {
        return null;
    }

    public class ModBlockMap {

    }
}
