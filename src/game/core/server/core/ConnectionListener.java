package game.core.server.core;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class ConnectionListener extends Thread implements Closeable {
    private final ServerSocket serverSocket;
    private final Consumer<Socket> socketConsumer;

    private volatile boolean terminate;

    public ConnectionListener(World server, int port) throws IOException {
        this(port, socket -> {
            try {
                server.registerClient(new ClientConnection(socket, server));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public ConnectionListener(int port, Consumer<Socket> socketConsumer) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.socketConsumer = socketConsumer;
        this.terminate = false;
        start();
    }

    @Override
    public void run() {
        while (!terminate) {
            try {
                Socket socket = serverSocket.accept();
                socketConsumer.accept(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws IOException {
        this.terminate = true;
        serverSocket.close();
    }
}
