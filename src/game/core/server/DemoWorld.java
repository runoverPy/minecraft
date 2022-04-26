package game.core.server;

import game.core.vanilla.TestWorldGenerator;

public final class DemoWorld extends GeneratedWorld {
    public DemoWorld() {
        super(new TestWorldGenerator());
    }
}
