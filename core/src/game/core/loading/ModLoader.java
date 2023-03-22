package game.core.loading;

import mdk.blocks.BlockBase;

import java.util.function.Supplier;

public class ModLoader implements mdk.ModLoader {
    private final String modName;

    public ModLoader(String modName) {
        this.modName = modName;
    }

    public void addDependency(String dependency) {

    }

    public void addBlock(String blockName, Supplier<? extends BlockBase> toRegister) {

    }
}

