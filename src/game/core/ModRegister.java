package game.core;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ModRegister {
    private final List<Mod> registeredMods;

    public ModRegister() {
        registeredMods = new LinkedList<>();
    }

    public void registerMod(Mod mod) {
        registeredMods.add(mod);
    }

    public Mod[] getActiveModHandles() {
        return registeredMods.toArray(new Mod[0]);
    }
}
