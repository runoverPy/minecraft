package game.assets.menus;

import game.assets.mcui.Align;
import game.assets.mcui.Component;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.FrameTile;
import game.assets.mcui.asset.PixelImageTile;
import game.assets.mcui.asset.TextTile;
import game.assets.mcui.container.AnchorPane;
import game.assets.mcui.container.ScrollPane;
import game.assets.mcui.container.VerticalContainer;
import game.assets.mcui.control.Button;
import game.assets.mcui.control.InputField;
import game.assets.mcui.control.Slider;
import game.main.Main;
import game.util.ImageFile;
import org.joml.Vector4f;

public class MCUITest extends Menu {
    private VerticalContainer testContainer;
    private ColorTile testColor;
    private PixelImageTile testImage;
    private InputField testInput;
    private TextTile testTextTile;
    private FrameTile testFrameTile;
    private AnchorPane testAnchorPane;
    private ScrollPane testScrollPane;
    private Button testButton;
    private Button returnButton;
    private Slider<String> testSlider;

    public MCUITest(MenuHandler handler) {
        Component.setScale(Component.ItemScale.LARGE);
        testColor = new ColorTile(new Vector4f(0, 0, 1, 1));
        testColor.setPxSize(16, 16);
        testImage = new PixelImageTile(ImageFile.loadImage("/img/stone.png"));
        testImage.setPxSize(32, 32);
        InputField testInput2 = new InputField();
        testInput2.setPxSize(128, 16);
        testInput = new InputField();
        testInput.setPxSize(128, 16);
        testTextTile = new TextTile(Main.getLogPath().toAbsolutePath().toString());
        testTextTile.setColor(new Vector4f(0, 0, 0, 1));
        testTextTile.setShade(new Vector4f(0.75f, 0.75f, 0.75f, 1f));
        testTextTile.setPxSize(256, 36);
        testTextTile.setAlign(Align.CENTER_LEFT);
        testTextTile.setShaded(true);
        testFrameTile = new FrameTile();
        testFrameTile.setPxSize(16, 16);
        testFrameTile.setThickness(3);
        testButton = new Button("Test Button");
        testButton.setPxSize(128, 16);
        returnButton = new Button("Return to Menu");
        returnButton.setPxSize(128, 16);
        returnButton.setOnAction(event -> handler.prev());
        testSlider = new Slider<>();
        String[] values = {"Lorem", "Ipsum", "dolor", "sit", "amet"};
        testSlider.setTransformer(new Slider.ArrayTransformer<>(values));
        testSlider.setPxSize(128, 16);

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
        PixelImageTile stupidStock = new PixelImageTile(ImageFile.loadImage("/img/dumb.png"));
        stupidStock.setPxSize(256, 183);
        testScrollPane = new ScrollPane();
        testScrollPane.setContent(stupidStock);
        testScrollPane.setPxSize(300, 100);


        testButton.setOnAction(() ->
          System.out.println(testInput.getContents()));

        testContainer = new VerticalContainer();
        testContainer.getChildren()
          .addAll(testColor, testImage, testInput, testInput2, testTextTile, testButton, returnButton, testSlider, testScrollPane);
        testContainer.setSize(256, 256);
        testContainer.setSpacing(4);
        setRoot(testContainer);
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
