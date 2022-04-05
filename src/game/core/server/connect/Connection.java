package game.core.server.connect;

import game.main.Main;
import org.json.simple.parser.ParseException;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Connection extends Thread implements Closeable {
    private final Socket socket;
    private final DataInputStream dataIn;
    private final DataOutputStream dataOut;
    private volatile boolean terminate;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.dataIn = new DataInputStream(socket.getInputStream());
        this.dataOut = new DataOutputStream(socket.getOutputStream());
    }

    public Connection(String addr, int port) throws IOException {
        this(new Socket(addr, port));
    }

    public void write(String s) {
        try {
            dataOut.writeUTF(s);
        } catch (IOException ioe) {
            Main.setError(ioe);
        }
    }

    private String read() throws IOException {
        return dataIn.readUTF();
    }

    @Override
    public void close() throws IOException {
        socket.close();
        dataIn.close();
        dataOut.close();
    }

    @Override
    public void run() {
        while (!terminate) {
            try {
                receivePacket(read());
            } catch (IOException ioe) {
                Main.setError(ioe);
                break;
            } catch (ParseException pe) {
                pe.printStackTrace(System.err);
            }
        }
    }

    private void terminate() {
        this.terminate = true;
    }

    /**
     * called once for every incoming packet, in order
     * @param packet incoming JSON packet as a string
     */
    protected abstract void receivePacket(String packet) throws ParseException;
}