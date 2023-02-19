package game.mechanics.entities;

import game.main.Main;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.json.JSONObject;

import static org.lwjgl.glfw.GLFW.*;

public class User {
    private final Vector3f pos;
    private double horizontal, vertical;

    public User() {
        this.pos = new Vector3f();
        this.horizontal = 0;
        this.vertical = 0;
    }

    public void resetCursor() {
        int[] nX = new int[1], nY = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), nX, nY);
        glfwSetCursorPos(Main.getWindowPtr(), nX[0] / 2.0, nY[0] / 2.0);
    }

    public Vector3f getCamPos() {
        return pos.add(0, 0, 1.5f);
    }

    public Vector3f getPos() {
        return pos;
    }

    public Vector3f getDir() {
        return new Vector3f(
                (float) (Math.cos(vertical) * Math.cos(horizontal)),
                (float) (Math.cos(vertical) * Math.sin(horizontal)),
                (float) Math.sin(vertical)
        );
    }

    public Matrix4f getProjViewMatrix(Matrix4f projMatrix) {
        int[] winW = new int[1], winH = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), winW, winH);

        Vector3f dir = getDir();

        Vector3f up = new Vector3f(
                (float) Math.cos(horizontal - Math.PI / 2),
                (float) Math.sin(horizontal - Math.PI / 2),
                (float) 0
        ).cross(dir);

        return projMatrix.lookAt(
                getCamPos(),
                getCamPos().add(dir),
                up
        );
    }

    public Matrix4f getProjectionView() {
        int[] winW = new int[1], winH = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), winW, winH);

        return getProjViewMatrix(new Matrix4f().perspective(
                (float)Math.PI/2,
                (float) winW[0] / winH[0],
                0.1f,
                100f
        ));

    }

    public UserStatus getUserStatus() {
        final long windowPtr = Main.getWindowPtr();
        return new UserStatus(
                glfwGetKey(windowPtr, GLFW_KEY_W) == GLFW_PRESS,
                glfwGetKey(windowPtr, GLFW_KEY_A) == GLFW_PRESS,
                glfwGetKey(windowPtr, GLFW_KEY_S) == GLFW_PRESS,
                glfwGetKey(windowPtr, GLFW_KEY_D) == GLFW_PRESS,
                glfwGetKey(windowPtr, GLFW_KEY_SPACE) == GLFW_PRESS,
                glfwGetKey(windowPtr, GLFW_KEY_LEFT_SHIFT) == GLFW_PRESS,
                glfwGetMouseButton(windowPtr, GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS,
                glfwGetMouseButton(windowPtr, GLFW_MOUSE_BUTTON_RIGHT) == GLFW_PRESS
        );
    }

    public void update(Entity.EntityUpdate update) {
        this.pos.set(update.getPos());
        this.horizontal = update.getHorizontal();
        this.vertical = update.getVertical();
    }

    public static class UserStatus {
        private final boolean fwd, left, back, right, up, down, lClick, rClick;

        public UserStatus(boolean fwd, boolean left, boolean back, boolean right, boolean up, boolean down, boolean lClick, boolean rClick) {
            this.fwd = fwd;
            this.left = left;
            this.back = back;
            this.right = right;
            this.up = up;
            this.down = down;
            this.lClick = lClick;
            this.rClick = rClick;
        }

        public boolean isFwd() {
            return fwd;
        }

        public boolean isLeft() {
            return left;
        }

        public boolean isBack() {
            return back;
        }

        public boolean isRight() {
            return right;
        }

        public boolean isUp() {
            return up;
        }

        public boolean isDown() {
            return down;
        }

        public boolean isRightClick() {
            return lClick;
        }

        public boolean isLeftClick() {
            return rClick;
        }

        public static JSONObject encode(UserStatus userStatus) {
            JSONObject object = new JSONObject();
            object.put("fwd", userStatus.isFwd());
            object.put("left", userStatus.isLeft());
            object.put("back", userStatus.isBack());
            object.put("right", userStatus.isRight());
            object.put("up", userStatus.isUp());
            object.put("down", userStatus.isDown());
            object.put("lClick", userStatus.isLeftClick());
            object.put("rClick", userStatus.isRightClick());
            return object;
        }

        public static UserStatus decode(JSONObject object) {
            return new UserStatus(
              object.getBoolean("fwd"),
              object.getBoolean("fwd"),
              object.getBoolean("fwd"),
              object.getBoolean("fwd"),
              object.getBoolean("fwd"),
              object.getBoolean("fwd"),
              object.getBoolean("fwd"),
              object.getBoolean("fwd")
            );
        }
    }
}

