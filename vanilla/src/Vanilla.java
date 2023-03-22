import mdk.ModLoader;
import mdk.blocks.BlockLoader;
import mdk.Mod;
import mdk.worldgen.WorldGeneratorLoader;

public class Vanilla extends Mod {
    @Override
    public void loadMod(ModLoader loader) {

    }

    public String getModName() {
        return "vanilla";
    }

    @Override
    public void defineBlocks(BlockLoader loader) {
        loader
                .register("vanilla::stone", Stone::new)
                .register("vanilla::dirt", Dirt::new)
                .register("vanilla::grass", Grass::new)
                .register("vanilla::stone_bricks", StoneBricks::new)
                .register("vanilla::bricks", Bricks::new)
                .register("vanilla::steinle", Steinle::new)
                .register("vanilla::blumen", Blumen::new);
    }

    @Override
    public void defineGenerators(WorldGeneratorLoader loader) {
        loader.register("vanilla::flat_test", seed -> new TestWorldGenerator());
    }

}
