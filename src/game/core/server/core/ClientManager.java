package game.core.server.core;

public abstract class ClientManager {
    public abstract boolean register(Client client);
    public abstract boolean disconnect(Client client);
}
