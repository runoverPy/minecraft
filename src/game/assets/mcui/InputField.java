package game.assets.mcui;

import game.assets.mcui.asset.ColorFrame;
import game.assets.mcui.asset.TextField;
import game.assets.mcui.event.KeyEvent;
import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCharCallbackI;

import java.util.function.Consumer;

import static org.lwjgl.glfw.GLFW.*;

public class InputField extends PixelComponent {
    private final ColorFrame inputFrame;
    private final ColorFrame inputField;
    private final TextField inputText;

    private StringBuffer contents;
    private boolean isSelected;
    private TimedKeyInput backspace;

    private final GLFWCharCallbackI charCallback = (window, codepoint) -> contents.append((char) codepoint);

    public InputField() {
        contents = new StringBuffer();
        inputFrame = new ColorFrame(new Vector4f(0, 0, 0, 1));
        inputField = new ColorFrame(new Vector4f(0, 0, 0, 1));
        inputText = new TextField(contents);
        backspace = new TimedKeyInput(GLFW_KEY_BACKSPACE);
    }

    @Override
    protected void onResize(int newWidth, int newHeight) {
        inputFrame.setDimensions(getAbsWidth(), getAbsHeight());
        inputField.setDimensions(getAbsWidth() - 2, getAbsHeight() - 2);
    }

    public String toString() {
        return contents.toString();
    }

    public StringBuffer getContents() {
        return contents;
    }

    @Override
    public void render(Matrix4f matrix4f, int cornerX, int cornerY) {
        int pxScale = getPxScale();

        inputFrame.render(matrix4f, cornerX, cornerY);
        inputField.render(matrix4f, cornerX + pxScale, cornerY + pxScale);

         if (isSelected && backspace.check() && contents.length() > 0) {
             contents.deleteCharAt(contents.length() - 1);
         }

        inputText.render(matrix4f, cornerX + pxScale * 2, cornerY + pxScale * 2);
    }

    private void toggleSelect() {
        if (isSelected) {
            isSelected = false;
            Main.getActiveWindow().delCharCallback(charCallback);
        } else {
            isSelected = true;
            Main.getActiveWindow().addCharCallback(charCallback);
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
            boolean isTriggered = glfwGetKey(Main.getWindowPtr(), key) == GLFW_PRESS;
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
            if (keyEvent.key == this.key) {
                if (keyEvent.action == GLFW_PRESS) isTriggered = true;
                else if (keyEvent.action == GLFW_RELEASE) isTriggered = false;
            }
        }
    }
}
