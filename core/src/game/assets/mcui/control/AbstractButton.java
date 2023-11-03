package game.assets.mcui.control;

import game.assets.event.EventRunnable;
import game.assets.mcui.Align;
import game.assets.mcui.PixelComponent;
import game.assets.event.ActionEvent;
import game.assets.event.EventProcessor;
import game.assets.mcui.Pixelated;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.PixelImageTile;
import game.assets.mcui.asset.TextTile;
import game.util.ImageFile;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.function.Supplier;

public abstract class AbstractButton extends PixelComponent implements Pixelated<ButtonPixelPeer> {
    public static final Supplier<Boolean> TRUE = () -> true, FALSE = () -> false;

    protected Supplier<Boolean> isActive;
    private boolean armed;
    private ButtonLogic logic = new ButtonLogic(this);
    private String desc;
    private EventProcessor<ActionEvent> onAction;

    private final ColorTile buttonCover;

    private final ColorTile buttonFrame;

    private final PixelImageTile buttonImage;
    private final TextTile description;
    protected static final ImageFile filler = ImageFile.loadImage("/img/stone.png");

    protected AbstractButton(String desc, Supplier<Boolean> activeCondition) {
        this.isActive = activeCondition;
        this.desc = desc;
        buttonCover = new ColorTile(new Vector4f(0, 0, 0, 0.5f));
        buttonCover.setParent(this);
        buttonFrame = new ColorTile(new Vector4f());
        buttonFrame.setParent(this);
        buttonImage = new PixelImageTile(filler);
        buttonImage.setParent(this);
        description = new TextTile(desc);
        description.setParent(this);
        description.setAlign(Align.CENTER);
        description.setShaded(true);
    }

    protected AbstractButton(String desc) {
        this(desc, TRUE);
    }

    public boolean isActive() {
        return isActive.get();
    }

    @Override
    public void render(Matrix4f matrix) {
        layoutIfScaleChanged();
        if (isActive()) {
            Vector4f frameColor = isHovering() ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);
            buttonFrame.setBoxColor(frameColor);
        }

        buttonFrame.render(matrix);
        buttonImage.render(matrix);
        description.render(matrix);
        if (!isActive()) {
            buttonCover.render(matrix);
        }
    }

    @Override
    public void layout() {
        buttonCover.setPxSize(getPxWidth(), getPxHeight());
        buttonFrame.setPxSize(getPxWidth(), getPxHeight());
        buttonImage.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
        buttonImage.setLayoutPos(getPxScale(), getPxScale());
        description.setPxSize((getPxWidth() - 2), (getPxHeight() - 2));
        description.setLayoutPos(getPxScale(), getPxScale());
    }

    @Override
    protected int minWidth() {
        return 2;
    }

    @Override
    protected int minHeight() {
        return 2;
    }

    public abstract void fire();

    public EventProcessor<ActionEvent> getOnAction() {
        return onAction;
    }

    public void setOnAction(EventProcessor<ActionEvent> onAction) {
        if (onAction == null) {
            if (this.onAction != null) {
                removeEventHandler(ActionEvent.ACTION, this.onAction);
                this.onAction = null;
            }
        } else {
            addEventHandler(ActionEvent.ACTION, onAction);
            this.onAction = onAction;
        }
    }

    public void setOnAction(EventRunnable<ActionEvent> onAction) {
        setOnAction((EventProcessor<ActionEvent>) onAction);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public ButtonPixelPeer getPeer() {
        return null;
    }
}
