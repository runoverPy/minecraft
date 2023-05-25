package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.*;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.FrameTile;
import game.assets.mcui.asset.ImageTile;
import game.assets.mcui.asset.TextTile;
import game.assets.mcui.container.AnchorPane;
import game.assets.mcui.container.StackContainer;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.InputField;
import game.assets.event.ActionEvent;
import game.assets.event.EventRunnable;
import game.assets.ui_elements.MCUIPort;
import game.util.Image;
import org.joml.Vector4f;

public class MCUITest extends UIELMenu {
    private ColorTile background;
    private VerticalContainer testContainer;
    private MCUIPort port;
    private ColorTile testColor;
    private ImageTile testImage;
    private InputField testInput;
    private TextTile testTextTile;
    private FrameTile testFrameTile;
    private AnchorPane testAnchorPane;
    private Button testButton;
    private Button returnButton;
    private float timeFactor = 2;

    public MCUITest(MenuHandler handler) {
        super(Background.DIRT, 256, 256);
        this.port = new MCUIPort(256, 256, 0, 0, getRoot(), null);
        insert(this.port);

        PixelComponent.setScale(PixelComponent.ItemScale.LARGE);

        background = new ColorTile(new Vector4f(1));
        background.setPxSize(256, 256);
        testColor = new ColorTile(new Vector4f(0, 0, 1, 1));
        testColor.setPxSize(16, 16);
        testImage = new ImageTile(Image.loadImage("/img/stone.png"));
        testImage.setPxSize(32, 32);
        testInput = new InputField();
        testInput.setPxSize(128, 16);
        testTextTile = new TextTile("Testing MCUI", new Vector4f(0, 0, 0, 1));
        testTextTile.setPxSize(128, 12);
        testFrameTile = new FrameTile();
        testFrameTile.setPxSize(16, 16);
        testFrameTile.setThickness(3);
        testButton = new Button("Test Button");
        testButton.setPxSize(128, 16);
        returnButton = new Button("Return to Menu");
        returnButton.setSize(128, 16);
        returnButton.setOnAction(event -> handler.prev());

        ColorTile background1 = new ColorTile(new Vector4f(0, 1, 1,1));
        background1.setPxSize(256, 80);
        AnchorPane.setTopAnchor(background1, 0.0);
        AnchorPane.setBottomAnchor(background1, 0.0);
        AnchorPane.setLeftAnchor(background1, 0.0);
        AnchorPane.setRightAnchor(background1, 0.0);
        ColorTile square = new ColorTile(new Vector4f(0, 1, 0, 1));
        square.setPxSize(64, 48);
        AnchorPane.setTopAnchor(square, 10.0);
        AnchorPane.setLeftAnchor(square, 40.0);
        testAnchorPane = new AnchorPane();
        testAnchorPane.getChildren()
            .addAll(background1, square);
        testAnchorPane.setSize(256, 80);

        testButton.setOnAction((EventRunnable<ActionEvent>) () ->
          System.out.println(testInput.getContents()));

        testContainer = new VerticalContainer();
        testContainer.getChildren()
          .addAll(testColor, testImage, testInput, testTextTile, testButton, returnButton, testAnchorPane);
        testContainer.setSize(256, 256);
        testContainer.setSpacing(4);
        StackContainer container = new StackContainer();
        container.getChildren()
          .addAll(background, testContainer);
        port.setRoot(container);
    }

    private static Vector4f hsvToRGB(float h, float s, float v) {
        h = (h - (float) Math.floor(h));
        int h1 = (int) (h * 6);
        float f = h * 6 - h1;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);

        return switch (h1) {
            case 0 -> new Vector4f(v, t, p, 1);
            case 1 -> new Vector4f(q, v, p, 1);
            case 2 -> new Vector4f(p, v, t, 1);
            case 3 -> new Vector4f(p, q, v, 1);
            case 4 -> new Vector4f(t, p, v, 1);
            case 5 -> new Vector4f(v, p, q, 1);
            default -> throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + h + ", " + s + ", " + v);
        };
    }
}
