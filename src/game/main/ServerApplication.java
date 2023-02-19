package game.main;

import game.core.GameRuntime;
import game.core.server.World;
import game.core.server.connect.ConnectionConfig;
import game.core.server.connect.ConnectionListener;
import game.core.vanilla.Vanilla;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;

public class ServerApplication {
    public static final Font DEFAULT_FONT = new Font("Monospaced", Font.PLAIN, 14);

    private static World world;
    private static ConnectionListener listener;

    public static void main(String[] args) {

        Border defaultBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)
        );
        JFrame frame = new JFrame();
        frame.setTitle("Server Control Panel");
        System.out.println(frame.getDefaultCloseOperation());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK);
        textPane.setForeground(Color.GREEN);
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textPane.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JScrollPane textScrollPane = new JScrollPane(textPane);
        textScrollPane.setBounds(10, 10, 450, 500);
        textScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        frame.add(textScrollPane);

        PrintStream out, err;
        out = createOutStream(textPane);
        err = createErrStream(textPane);
        System.setOut(out);
        System.setErr(err);

        JTextField cmdInput = new JTextField();
        cmdInput.setBounds(10, 525, 450, 30);
        cmdInput.setFont(new Font("Monospaced", Font.PLAIN, 14));
        cmdInput.addActionListener(e -> {
            // write contents to stream or whatnot
            SimpleAttributeSet attributeSet = new SimpleAttributeSet();
            StyleConstants.setForeground(attributeSet, Color.RED);
            StyleConstants.setBackground(attributeSet, Color.YELLOW);
            StyleConstants.setBold(attributeSet, true);
            out.println(cmdInput.getText());
            cmdInput.setText("");
        });
        cmdInput.setBorder(defaultBorder);
        frame.add(cmdInput);

        JTextArea portTitle = new JTextArea();
        portTitle.setBounds(475, 10, 150, 25);
        portTitle.setEditable(false);
        portTitle.setText("Port:");
        portTitle.setFont(DEFAULT_FONT);
        portTitle.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        portTitle.setBackground(frame.getBackground());
        frame.add(portTitle);

        JTextField portInput = new JTextField();
        portInput.setBounds(475, 40, 150, 25);
        portInput.setText(Integer.toString(ConnectionConfig.DEFAULT_PORT));
        portInput.setFont(DEFAULT_FONT);
        portInput.setBorder(defaultBorder);
        frame.add(portInput);


        JButton startServer = new JButton("Start Server");
        startServer.setBounds(475, 530, 150, 25);
        frame.add(startServer);

        JButton closeServer = new JButton("Close Server");
        closeServer.setBounds(635, 530, 150, 25);
        closeServer.setEnabled(false);
        frame.add(closeServer);

        startServer.addActionListener(e -> {
            int port = Integer.parseInt(portInput.getText());
            if (port > Math.pow(2, 16) - 1) {
                // err
                return;
            }
            System.out.println("opening server");
            try {
                openWorld();
                openServer(port);
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            } finally {
                startServer.setEnabled(false);
                closeServer.setEnabled(true);
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        });
        closeServer.addActionListener(e -> {
            System.out.println("closing server");
            try {
                closeServer();
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            } finally {
                startServer.setEnabled(true);
                closeServer.setEnabled(false);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });

        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private static void openWorld() {
        GameRuntime.setInstance(new Vanilla());
        world = World.demoWorld();
    }

    private static void openServer(int port) throws IOException {
        if (world == null) throw new NullPointerException();
        listener = new ConnectionListener(world, port);
    }

    private static void closeServer() throws IOException {
        if (listener == null | world == null) {
            throw new NullPointerException();
        } else {
            listener.close();
            world.close();
        }
    }

    public static PrintStream createOutStream(JTextPane textPane) {
        StyledDocument document = textPane.getStyledDocument();
        OutputStream backOut = new OutputStream() {
            private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            @Override
            public void write(int data) {
                buffer.write(data & 0xff);
            }

            @Override
            public void flush() {
                try {
                    document.insertString(document.getLength(), new String(buffer.toByteArray(), StandardCharsets.UTF_8), null);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                buffer.reset();
            }
        };
        return new PrintStream(backOut, true);
    }

    public static PrintStream createErrStream(JTextPane textPane) {
        SimpleAttributeSet errAttributes = new SimpleAttributeSet();
        StyleConstants.setForeground(errAttributes, Color.RED);

        StyledDocument document = textPane.getStyledDocument();

        OutputStream backErr = new OutputStream() {
            private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            @Override
            public void write(int data) {
                buffer.write(data & 0xff);
            }

            @Override
            public void flush() {
                try {
                    document.insertString(
                            document.getLength(),
                            new String(buffer.toByteArray(), StandardCharsets.UTF_8),
                            errAttributes
                    );
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                buffer.reset();
            }
        };

        return new PrintStream(backErr, true);
    }
}
