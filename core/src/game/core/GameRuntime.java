package game.core;

import game.core.loading.*;
import mdk.Mod;
import game.core.rendering.MaterialDatabase;

import java.nio.file.Paths;
import java.util.Arrays;

public class GameRuntime {
    private final ModRegister modRegister;
    private final ModHandler modHandler;
    private final BlockRegister blockRegister;
    private final MaterialDatabase materialDatabase;
    private final WorldGeneratorRegister generatorRegister;

    private static GameRuntime instance;

    private GameRuntime() {
        modRegister = new ModRegister();
        modRegister.acquireMods(Paths.get("core/mods/"));
        blockRegister = new BlockRegister();
        materialDatabase = new MaterialDatabase();
        generatorRegister = new WorldGeneratorRegister();
        modHandler = new ModHandler(modRegister.getActiveModHandles(), this);
        System.out.println(Arrays.toString(generatorRegister.getLoadedGenerators()));
    }

    public static void setInstance() {
        if (instance != null) throw new IllegalStateException("A new game runtime cannot be created without terminating the current game runtime");
        instance = new GameRuntime();
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

    public MaterialDatabase getMaterialDatabase() {
        return this.materialDatabase;
    }

    public WorldGeneratorRegister getGeneratorRegister() {
        return generatorRegister;
    }
}
