package game.assets.widgets;

import game.main.Main;
import game.mechanics.input.CooldownKeyInput;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
import static org.lwjgl.glfw.GLFW.glfwSetCharCallback;

class Query extends ClickFrame {
    private StringBuffer contents;
    private boolean selected;
    private final CooldownKeyInput backspace;

    private static Runnable onLeave = () -> {};

    public Query(int width, int height, int xOffset, int yOffset, Box parent, StringBuffer buffer) {
        super(width, height, xOffset, yOffset, parent);
        contents = buffer;
        backspace = new CooldownKeyInput(GLFW_KEY_BACKSPACE, 100);
    }

    public Query(int width, int height, int xOffset, int yOffset, Box parent) {
        this(width, height, xOffset, yOffset, parent, new StringBuffer());
    }

    private void addChar(char c) {
        contents.append(c);
    }

    private void select() {
        onLeave.run();
        onLeave = this::leave;
        selected = true;
        glfwSetCharCallback(Main.getWindowPtr(), ((window, codepoint) -> {
            addChar((char) codepoint);
        }));
    }

    private void leave() {
        onLeave = () -> {};
        selected = false;
    }

    private void toggleSelect() {
        if (selected) leave();
        else select();
    }

    public String getContents() {
        return contents.toString();
    }

    @Override
    public void draw(int pxScale, Matrix4f matrixPV) {
        update(pxScale);
        if (released()) toggleSelect();

        Vector4f frameColor = selected ? new Vector4f(1, 1, 1, 1) : new Vector4f(0, 0, 0, 1);

        ColorBox.draw(width, height, 0, 0, this, frameColor, pxScale, matrixPV);
        ColorBox.draw(width - 2, height - 2, 1, 1, this, new Vector4f(0, 0, 0, 1), pxScale, matrixPV);

        if (backspace.check() && contents.length() > 0) {
            contents.setLength(contents.length() - 1);
        }

        TextBox queryText = new TextBox(width - 4, height - 4, 2, 2, this, getContents() + (selected ? "_" : ""), false, false);
        queryText.draw(pxScale, matrixPV);
    }
}
