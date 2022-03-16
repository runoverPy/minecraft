package game.core;

import game.mechanics.blocks.Block;
import game.mechanics.blocks.base.BlockBase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockRegister {
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

    public void register(String registryName, Supplier<? extends BlockBase> toRegister) {
        if (registeredBlocks.containsKey(registryName)) throw new RegistrationException("Block already registered");
        else registeredBlocks.put(registryName, toRegister);
    }

    public BlockBase createBlockByName(String blockName) {
        if (registeredBlocks.containsKey(blockName)) return registeredBlocks.get(blockName).get();
        else throw new RegistrationException("Block not registered");
    }

    public final boolean testJSONConversion(Block block) {
        return block.equals(decodeBlock(encodeBlock(block)));
    }

    public ModBlockRegister getModBlockRegister(String modName) {
        return new ModBlockRegister(modName);
    }

    public Supplier<? extends BlockBase> getBlockGenerator(String blockName) {
        return registeredBlocks.get(blockName);
    }

    public class ModBlockRegister implements AutoCloseable {
        private final String modName;

        private ModBlockRegister(String modName) {
            this.modName = modName;
        }

        public void register(String blockName, Supplier<? extends BlockBase> toRegister) {
            String fullBlockName = modName + "::" + blockName;
            if (registeredBlocks.containsKey(fullBlockName)) throw new RegistrationException("Block already registered");
            else registeredBlocks.put(fullBlockName, toRegister);
        }

        @Override
        public void close() throws Exception {

        }
    }
}
