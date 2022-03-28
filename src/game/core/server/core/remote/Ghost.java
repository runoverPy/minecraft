package game.core.server.core.remote;

public abstract class Ghost {
    private final int entityID;

    public Ghost(int entityID) {
        this.entityID = entityID;
    }

    public final void update() {}
}
