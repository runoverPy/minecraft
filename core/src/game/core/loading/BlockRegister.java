package game.core.loading;

import game.core.server.Block;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import mdk.blocks.BlockLoader;
import mdk.blocks.BlockBase;

/*
Block name syntax: mod.hierarchy:block.module.hierarchy
 */
public class BlockRegister {
    private final Map<String, PackageBlockLoader> modBlockLoaders;

    public BlockRegister() {
        modBlockLoaders = new HashMap<>();
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

    public BlockBase createBlockBaseByName(String blockName) {
        String[] split = blockName.split(":", 2);
        if (split.length == 1)
            throw new IllegalArgumentException("Illegal block name format: must contain exactly one \":\"-character");
        else if (split[0].equals(""))
            throw new IllegalArgumentException("Illegal block name format: mod name must be given before colon");
        else if (split[1].contains(":"))
            throw new IllegalArgumentException("Illegal block name format: must contain exactly one \":\"-character");
        else {
            BlockBase blockBase = modBlockLoaders.get(split[0]).createBlockBase(split[1]);
            if (blockBase == null) throw new RegistrationException("Block " + blockName + " not registered");
            return blockBase;
        }
    }

    public boolean isLoaded(String blockName) {
        String[] split = blockName.split(":", 2);
        if (split.length == 1)
            throw new IllegalArgumentException("Illegal block name format: must contain exactly one \":\"-character");
        else if (split[0].equals(""))
            throw new IllegalArgumentException("Illegal block name format: mod name must be given before colon");
        else if (split[1].contains(":"))
            throw new IllegalArgumentException("Illegal block name format: must contain exactly one \":\"-character");
        else
            return modBlockLoaders.containsKey(split[0]) && modBlockLoaders.get(split[0]).isLoaded(split[1]);
    }

    public BlockLoader getModBlockLoader(String modName) {
        if (modBlockLoaders.containsKey(modName)) return modBlockLoaders.get(modName);
        else {
            PackageBlockLoader packageBlockLoader = new PackageBlockLoader();
            modBlockLoaders.put(modName, packageBlockLoader);
            return packageBlockLoader;
        }
    }

    private static class PackageBlockLoader implements BlockLoader {
        private final Map<String, Supplier<? extends BlockBase>> registeredBlocks;
        private final Map<String, PackageBlockLoader> subPackages;

        PackageBlockLoader() {
            registeredBlocks = new HashMap<>();
            subPackages = new HashMap<>();
        }

        @Override
        public BlockLoader register(String registryName, Supplier<? extends BlockBase> toRegister) {
            if (registryName.contains(":") || registryName.contains("."))
                throw new IllegalArgumentException("registry name may not contain \".\" or \":\"");
            if (registeredBlocks.containsKey(registryName)) throw new RegistrationException("Block already registered");
            registeredBlocks.put(registryName, toRegister);
            return this;
        }

        @Override
        public BlockLoader subPackage(String packageName, Consumer<BlockLoader> packageContents) {
            if (subPackages.containsKey(packageName)) packageContents.accept(subPackages.get(packageName));
            else {
                PackageBlockLoader subPackage = new PackageBlockLoader();
                subPackages.put(packageName, subPackage);
                packageContents.accept(subPackage);
            }
            return this;
        }

        public boolean isLoaded(String blockName) {
            String[] split = blockName.split("\\.", 2);
            if (split.length == 1) {
                return registeredBlocks.containsKey(split[0]);
            } else {
                return subPackages.containsKey(split[0]) && subPackages.get(split[0]).isLoaded(split[1]);
            }
        }

        public BlockBase createBlockBase(String blockName) {
            String[] split = blockName.split("\\.", 2);
            if (split.length == 1) {
                return registeredBlocks.get(split[0]).get();
            } else {
                if (!subPackages.containsKey(blockName)) return null;
                return subPackages.get(split[0]).createBlockBase(split[1]);
            }
        }
    }
}
