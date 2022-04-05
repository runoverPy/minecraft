package game.core.server.connect;

import game.core.server.Client;

public abstract class ClientManager {
    public abstract boolean register(Client client);
    public abstract boolean disconnect(Client client);
}
