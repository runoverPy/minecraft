package game.core;

import game.mechanics.blocks.base.BlockBase;

import java.util.function.Supplier;

public class ModLoader {
    private final String modName;

    public ModLoader(String modName) {
        this.modName = modName;
    }

    public void addDependency(String dependency) {}
    public void addBlock(String blockName, int blockID, Supplier<? extends BlockBase> toRegister) {

    }
}

