package game.assets.menus;

import game.assets.Background;
import game.assets.widgets.Widget;
import game.core.server.connect.ConnectionConfig;
import game.util.Fn;

class Multiplayer extends Menu {
    public Multiplayer(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);
        StringBuffer addr = new StringBuffer(), port = new StringBuffer();

        Fn onJoinWorld = () -> {
            String serverAddr = addr.toString();
            int serverPort;
            try {
                serverPort = Integer.parseInt(port.toString());
            } catch (NumberFormatException nfe) {
                serverPort = ConnectionConfig.DEFAULT_PORT;
            }
            System.out.println("Address:" + " " + serverAddr);
            System.out.println("Port:" + " " + serverPort);
            System.out.println("Keinem Server wurde beigetreten, da diese Funktion noch nicht eingeführt wurde.");
            // Main.setScene(Game.joinGame(serverAddr, serverPort));
        };

        try (WidgetManager manager = organiser(192, 22, 4, 4)) {
            manager.insert(Widget.query(addr));
            manager.insert(Widget.query(port));
            manager.insert(Widget.button("Join World", onJoinWorld));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
