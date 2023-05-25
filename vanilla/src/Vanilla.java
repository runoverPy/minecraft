import mdk.ModLoader;
import mdk.Mod;

public class Vanilla extends Mod {
    @Override
    public void loadMod(ModLoader loader) {
        loader.loadBlocks()
                .register("stone", Stone::new)
                .register("dirt", Dirt::new)
                .register("grass", Grass::new)
                .register("stone_bricks", StoneBricks::new)
                .register("bricks", Bricks::new)
                .register("steinle", Steinle::new)
                .register("blumen", Blumen::new);
        loader.loadGenerators()
                .register("vanilla:flat_test", TestWorldGenerator.class)
                .register("vanilla:noise", NoiseGenerator.class);
        /*
        loader.blockLoader().loadBlocks(loader -> { loader
            .register(...);
        }).package
        loader.loadBlocks().addPackage("Test")
            .register(...);
         */
    }

    public String getModName() {
        return "vanilla";
    }
}
