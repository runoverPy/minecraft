package game.core.modding;


import game.core.BlockRegister;
import game.core.ModCore;
import game.core.ModLoader;
import game.core.WorldGeneratorRegister;

@ModCore
public abstract class Mod {
    public abstract void loadMod(ModLoader loader);



    public abstract String getModName();
    public abstract void defineBlocks(BlockRegister register);
    public abstract void defineGenerators(WorldGeneratorRegister register);
}
