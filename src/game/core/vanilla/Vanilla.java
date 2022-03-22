package game.core.vanilla;

import game.core.BlockRegister;
import game.core.ModLoader;
import game.core.WorldGeneratorRegister;
import game.core.Mod;

public class Vanilla extends Mod {
    @Override
    public void loadMod(ModLoader loader) {

    }

    public String getModName() {
        return "vanilla";
    }

    @Override
    public void defineBlocks(BlockRegister register) {
        register.register("vanilla::stone", Stone::new);
        register.register("vanilla::bricks", Bricks::new);
        register.register("vanilla::steinle", Steinle::new);
    }

    @Override
    public void defineGenerators(WorldGeneratorRegister register) {
        register.register("flat_test", seed -> new TestWorldGenerator());
    }

}
