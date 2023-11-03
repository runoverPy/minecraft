package game.assets.menus;

import game.assets.Background;
import game.assets.event.ActionEvent;
import game.assets.event.EventRunnable;
import game.assets.mcui.Align;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.InputField;
import game.core.GameManager;
import game.core.server.connect.ConnectionConfig;
import game.main.Main;

import java.io.IOException;

class Multiplayer extends Menu {
    public Multiplayer(MenuHandler handler) {
        super(Background.BRICKS);
        StringBuffer addr = new StringBuffer(), port = new StringBuffer();

        EventRunnable<ActionEvent> onJoinWorld = () -> {
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

        StackContainer outerContainer = new StackContainer();
        outerContainer.setAlign(Align.CENTER);
        VerticalContainer innerContainer = new VerticalContainer();
        innerContainer.setAlign(Align.CENTER);
        innerContainer.setSize(-1, -1);
        innerContainer.setSpacing(2);

        InputField addrInputField = new InputField();
        addrInputField.setPxSize(192, 16);
        addrInputField.setContents(addr);
        InputField portInputField = new InputField();
        portInputField.setPxSize(192, 16);
        portInputField.setContents(port);
        Button joinButton = new Button("Join World");
        joinButton.setOnAction(onJoinWorld);
        joinButton.setPxSize(192, 16);
        Button returnButton = new Button("Back");
        returnButton.setOnAction(handler::prev);
        returnButton.setPxSize(192, 16);

        innerContainer.getChildren()
          .addAll(addrInputField, portInputField, joinButton, returnButton);
        outerContainer.getChildren()
          .setAll(innerContainer);
        setRoot(outerContainer);
    }
}
