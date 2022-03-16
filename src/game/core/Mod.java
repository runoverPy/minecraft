package game.core;


@ModCore
public abstract class Mod {
    public abstract String getModName();
    public abstract void defineBlocks(BlockRegister register);
    public abstract void defineGenerators(WorldGeneratorRegister register);
}
