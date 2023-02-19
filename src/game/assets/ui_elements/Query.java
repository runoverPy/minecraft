package game.assets.ui_elements;

import game.main.Main;
import game.mechanics.input.CooldownKeyInput;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFWCharCallbackI;

import javax.swing.*;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;

class Query extends ChildBox {
    private final ColorBox queryFrame;
    private final ColorBox queryField;
    private StringBuffer contents;
    private boolean selected;
    private final CooldownKeyInput backspace;

    private GLFWCharCallbackI charCallback = (window, codepoint) -> {
        System.out.println(codepoint);
        addChar((char) codepoint);
    };

    public Query(int width, int height, int xOffset, int yOffset, Component parent, StringBuffer buffer) {
        super(width, height, xOffset, yOffset, parent);
        contents = buffer;
        queryFrame = new ColorBox(getWidth(), getHeight(), 0, 0, this, new Vector4f(0, 0, 0, 1));
        queryField = new ColorBox(getWidth() - 2, getHeight() - 2, 1, 1, this, new Vector4f(0, 0, 0, 1));
        backspace = new CooldownKeyInput(GLFW_KEY_BACKSPACE, 100);
    }

    private void addChar(char c) {
        contents.append(c);
    }

    private void select() {
        selected = true;
        Main.getActiveWindow().addCharCallback(charCallback);
    }

    private void leave() {
        selected = false;
        Main.getActiveWindow().delCharCallback(charCallback);
    }

    private void toggleSelect() {
        if (selected) leave();
        else select();
    }

    @Override
    public void draw(int pxScale, Matrix4f matrixPV) {
        queryFrame.draw(pxScale, matrixPV);
        queryField.draw(pxScale, matrixPV);

        if (backspace.check() && contents.length() > 0) {
            contents.setLength(contents.length() - 1);
        }

        TextBox queryText = new TextBox(getWidth() - 4, getHeight() - 4, 2, 2, this, contents.toString() + (selected ? "_" : ""), false, false);
        queryText.draw(pxScale, matrixPV);
    }

    @Override
    public boolean onMouseEvent(MouseEvent event, int pxScale) {
        if (event.eventType == MouseEvent.EventType.RELEASED && contains(event, pxScale)) {
            toggleSelect();
            Vector4f frameColor = selected ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);
            queryFrame.setBoxColor(frameColor);
            return true;
        }
        return false;
    }
}
