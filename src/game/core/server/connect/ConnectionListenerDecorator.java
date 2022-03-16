package game.core.server.connect;

import game.core.server.core.Server;

import java.io.IOException;

public class ConnectionListenerDecorator extends ConnectivityDecorator {
    private final ConnectionListener listener;

    protected ConnectionListenerDecorator(Server server, int port) throws IOException {
        super(server);
        this.listener = new ConnectionListener(this, port);
        this.listener.start();
    }

    @Override
    public void close() throws IOException {
        super.close();
        this.listener.close();
    }
}
