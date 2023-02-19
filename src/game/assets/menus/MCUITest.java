package game.assets.menus;

import game.assets.Background;
import game.assets.mcui.*;
import game.assets.mcui.asset.ColorFrame;
import game.assets.mcui.asset.ImageFrame;
import game.assets.mcui.asset.TextField;
import game.assets.ui_elements.Button;
import game.assets.ui_elements.MCUIPort;
import game.util.Image;
import org.joml.Vector4f;

public class MCUITest extends Menu {
    private ColorFrame background;
    private LinearContainer testContainer;
    private MCUIPort port;
    private ColorFrame testColor;
    private ImageFrame testImage;
    private InputField testInput;
    private TextField testTextField;

    public MCUITest(MenuHandler handler) {
        super(Background.CLEAR, 256, 256);
        this.port = new MCUIPort(128, 128, 64, 64, getRoot(), new UnsafeContainer());
        // insert(new ColorBox(128, 128, 64, 64, getRoot(), new Vector4f(1)));
        insert(this.port);
        insert(new Button(96, 20, 80, 196, getRoot(), "Return to Menu", handler::prev));

        PixelComponent.setScale(ItemScale.LARGE);

        background = new ColorFrame(new Vector4f(1));
        background.setDimensions(128, 128);
        testContainer = new LinearContainer();
        background.setDimensions(128, 128);
        testColor = new ColorFrame(new Vector4f(0, 0, 1, 1));
        testColor.setDimensions(16, 16);
        testImage = new ImageFrame(Image.loadImage("/img/stone.png"));
        testImage.setDimensions(32, 32);
        testInput = new InputField();
        testInput.setDimensions(64, 14);
        testTextField = new TextField("Testing MCUI", new Vector4f(0, 0, 0, 1));
        testTextField.setDimensions(0, 12);
        testContainer.append(testColor);
        testContainer.append(testImage);
        testContainer.append(testInput);
        testContainer.append(testTextField);
        port.getRoot().append(background);
        port.getRoot().append(testContainer);
    }
}
