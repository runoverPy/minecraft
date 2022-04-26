package game.core;

import game.core.modding.Mod;

public class GameRuntime {
    private final ModHandler modHandler;

    private final BlockRegister blockRegister;
    private final WorldGeneratorRegister generatorRegister;

    private static GameRuntime instance;

    private GameRuntime(Mod[] activeModHandles) {
        blockRegister = new BlockRegister();
        generatorRegister = new WorldGeneratorRegister();
        modHandler = new ModHandler(activeModHandles, this);
    }

    public static void setInstance(Mod... activeModHandles) {
        if (instance != null) throw new IllegalStateException("A new game runtime cannot be created without terminating the current game runtime");
        instance = new GameRuntime(activeModHandles);
    }

    /**
     *
     * @return the current active game runtime
     * @throws IllegalStateException if no game runtime has been declared or the previous one was terminated and not replaced
     */
    public static GameRuntime getInstance() throws IllegalStateException {
        if (instance == null) throw new IllegalStateException("No game runtime has been initialized");
        return instance;
    }

    public ModHandler getModHandler() {
        return modHandler;
    }

    public BlockRegister getBlockRegister() {
        return this.blockRegister;
    }

    public WorldGeneratorRegister getGeneratorRegister() {
        return generatorRegister;
    }
}
