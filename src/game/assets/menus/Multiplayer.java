package game.assets.menus;

import game.assets.Background;
import game.assets.ui_elements.Widget;
import game.core.GameManager;
import game.core.server.connect.ConnectionConfig;
import game.main.Main;

import java.io.IOException;

class Multiplayer extends Menu {
    public Multiplayer(MenuHandler handler) {
        super(Background.BRICKS, 256, 256);
        StringBuffer addr = new StringBuffer(), port = new StringBuffer();

        Runnable onJoinWorld = () -> {
            String serverAddr = addr.toString();
            int serverPort;
            try {
                serverPort = Integer.parseInt(port.toString());
            } catch (NumberFormatException nfe) {
                serverPort = ConnectionConfig.DEFAULT_PORT;
            }
            System.out.println("Address:" + " " + serverAddr);
            System.out.println("Port:" + " " + serverPort);
            try {
                Main.openGame(GameManager.joinGame(serverAddr, serverPort));
            } catch (IOException e) {
                Main.setError(e);
            }
        };

        try (WidgetOrganizer manager = organiser(192, 18, 4, 4)) {
            manager.insert(Widget.query(addr));
            manager.insert(Widget.query(port));
            manager.insert(Widget.button("Join World", onJoinWorld));
            manager.insert(Widget.button("Back", handler::prev));
        }
    }
}
