package game.assets.mcui.control;

import game.assets.mcui.Align;
import game.assets.mcui.PixelComponent;
import game.assets.mcui.asset.ColorTile;
import game.assets.mcui.asset.TextTile;
import game.assets.event.KeyEvent;
import game.main.Main;
import game.window.CharCallback;
import game.window.KeyCallback;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.function.Consumer;

import static org.lwjgl.glfw.GLFW.*;

public class InputField extends PixelComponent {
    private final ColorTile inputFrame;
    private final ColorTile inputField;
    private final TextTile inputText;

    private StringBuffer contents;
    private boolean isSelected;
    private InputFieldLogic logic;


    private final CharCallback charCallback = codepoint ->
            contents.append((char) codepoint);
    private final KeyCallback backspaceCallback = (key, scancode, action, mods) -> {
        if (key == GLFW_KEY_BACKSPACE && (action == GLFW_PRESS || action == GLFW_REPEAT) && contents.length() > 0)
            contents.deleteCharAt(contents.length() - 1);
    };

    public InputField() {
        contents = new StringBuffer();
        inputFrame = new ColorTile(new Vector4f(0, 0, 0, 1));
        inputFrame.setParent(this);
        inputField = new ColorTile(new Vector4f(0, 0, 0, 1));
        inputField.setParent(this);
        inputText = new TextTile();
        inputText.setParent(this);
        inputText.setAlign(Align.CENTER_LEFT);
        inputText.setShaded(true);
        logic = new InputFieldLogic(this);
    }

    @Override
    public void layout() {
        inputFrame.setPxSize(getPxWidth(), getPxHeight());
        inputField.setPxSize(getPxWidth() - 2, getPxHeight() - 2);
        inputField.setLayoutPos(getPxScale(), getPxScale());
        inputText.setPxSize(getPxWidth() - 2, getPxHeight() - 2);
        inputText.setLayoutPos(getPxScale() * 2, getPxScale() * 2);
    }

    public String toString() {
        return super.toString() + "[\"" + contents.toString() + "\"]";
    }

    public StringBuffer getContents() {
        return contents;
    }

    public void setContents(StringBuffer contents) {
        this.contents = contents;
    }

    @Override
    public void render(Matrix4f matrix) {
        layoutIfScaleChanged();

        inputFrame.render(matrix);
        inputField.render(matrix);
//
//         if (isSelected && backspace.check() && contents.length() > 0) {
//             contents.deleteCharAt(contents.length() - 1);
//         }
        inputText.setText(contents.toString() + (isSelected ? "_" : ""));
        inputText.render(matrix);
    }

    public void toggleSelect() {
        System.err.println("Toggling selection");
        if (isSelected) {
            isSelected = false;
            inputFrame.setBoxColor(new Vector4f(0, 0, 0, 1));
            Main.getActiveWindow().delCharCallback(charCallback);
            Main.getActiveWindow().delKeyCallback(backspaceCallback);
        } else {
            isSelected = true;
            inputFrame.setBoxColor(new Vector4f(1, 1, 1, 1));
            Main.getActiveWindow().addCharCallback(charCallback);
            Main.getActiveWindow().addKeyCallback(backspaceCallback);
        }
    }

    private static class TimedKeyInput implements Consumer<KeyEvent> {
        private static final long initialDelay = 400;
        private static final long subsequentDelay = 25;

        private final int key;

        private boolean isTriggered;
        private long lastTrigger;
        private int triggerCount;

        public TimedKeyInput(int key) {
            this.key = key;
        }

        public boolean check() {
            boolean out = false;
            boolean isTriggered = Main.getActiveWindow().getKey(key) == GLFW_PRESS;
            long now = System.currentTimeMillis();
            long cooldown = triggerCount == 1 ? initialDelay : subsequentDelay;
            if (isTriggered) {
                if (triggerCount == 0 || now - lastTrigger >= cooldown) {
                    lastTrigger = now;
                    out = true;
                    triggerCount++;
                }
            } else {
                triggerCount = 0;
            }
            return out;
        }

        @Override
        public void accept(KeyEvent keyEvent) {
//            if (keyEvent.key == this.key) {
//                if (keyEvent.action == GLFW_PRESS) isTriggered = true;
//                else if (keyEvent.action == GLFW_RELEASE) isTriggered = false;
//            }
        }
    }
}
