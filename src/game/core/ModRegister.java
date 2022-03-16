package game.core;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ModRegister {
    private static ModRegister instance = null;

    private final List<Mod> registeredMods;

    private ModRegister() {
        registeredMods = new LinkedList<>();
    }

    public static ModRegister getInstance() {
        if (instance == null) instance = new ModRegister();
        return instance;
    }

    public void registerMod(Mod mod) {
        registeredMods.add(mod);
    }

    public Mod[] getActiveModHandles() {return null;}
}
