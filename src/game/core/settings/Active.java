package game.core.settings;

import game.util.Cyclic;

public enum Active implements Cyclic<Active> {
    ON ("On"),
    OFF ("Off");

    Active(String name) {
        this.name = name;
    }

    private final String name;

    @Override
    public Active next() {
        switch (this) {
            case ON: return OFF;
            case OFF: return ON;
            default: return null;
        }
    }

    public String toString() {
        return name;
    }
}
