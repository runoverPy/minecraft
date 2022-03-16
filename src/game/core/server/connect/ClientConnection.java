package game.core.server.connect;

import game.core.server.core.Client;
import game.core.server.core.Server;
import org.joml.Vector3i;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Connection implements Client {
    private final Server server;

    public ClientConnection(Socket socket, Server server) throws IOException {
        super(socket);
        this.server = server;
    }

    @Override
    public void updateBlock(Vector3i block) {

    }

    @Override
    public void updateBlock(int x, int y, int z) {

    }

    @Override
    public void loadChunk(int cX, int cY, int cZ) {

    }

    @Override
    public void close() throws IOException {
        server.disconnectClient(this);
        super.close();
    }

    @Override
    protected void receivePacket(String packet) {

    }
}