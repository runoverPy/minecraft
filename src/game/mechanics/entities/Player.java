package game.mechanics.entities;

import game.main.Main;
import game.mechanics.blocks.*;
import game.mechanics.collision.Direction;
import game.mechanics.input.CooldownMouseInput;
import game.core.server.core.Server;
import game.core.GameManager;
import game.util.Ray;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.EnumMap;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Puppet {
    /**
     * the current movement mode
     */
    private MovementMode movementMode;
    private static final double rotSpeed = 2 * Math.PI / 10000f, movSpeed = 5 / 1000f;
    private final CooldownMouseInput blockBreak, blockPlace;


    public Player(Server server) {
        this(server, server.getSpawnPoint(), 0, 0);
    }

    public Player(Server server, Vector3i pos, float vertical, float horizontal) {
        this(server, new Vector3f(pos), vertical, horizontal);
    }

    public Player(Server server, Vector3f pos, float vertical, float horizontal) {
        super(server, pos, horizontal, vertical);
        this.movementMode = MovementMode.WALKING;
        this.blockBreak = new CooldownMouseInput(GLFW_MOUSE_BUTTON_LEFT, 250, true);
        this.blockPlace = new CooldownMouseInput(GLFW_MOUSE_BUTTON_RIGHT, 250, true);
        reset();
    }

    @Override
    public void move(Vector3f posChange, Vector3f velChange, EnumMap<Direction, Boolean> collisions, long dT) {
        double[] curX = new double[1], curY = new double[1];
        int[] winW = new int[1], winH = new int[1];
        glfwGetCursorPos(Main.getWindowPtr(), curX, curY);
        glfwGetWindowSize(Main.getWindowPtr(), winW, winH);
        glfwSetCursorPos(Main.getWindowPtr(), winW[0] / 2.0, winH[0] / 2.0);

        addHorizontal((winW[0] / 2.0 - curX[0]) * dT * rotSpeed);
        addVertical((winH[0] / 2.0 - curY[0]) * dT * rotSpeed);

        if (getVertical() < - Math.PI / 2) setVertical(- Math.PI / 2);
        if (getVertical() > Math.PI / 2) setVertical(Math.PI / 2);

        Vector3f fwd, right, up;
        fwd = new Vector3f(
                (float) Math.cos(getHorizontal()),
                (float) Math.sin(getHorizontal()),
                (float) 0
        );
        right = new Vector3f(
                (float) Math.cos(getHorizontal() - Math.PI / 2),
                (float) Math.sin(getHorizontal() - Math.PI / 2),
                (float) 0
        );
        up = new Vector3f(0, 0, 1);

        switch (movementMode) {
            case FLYING: {
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_W) == GLFW_PRESS) {
                    posChange.add(fwd.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_A) == GLFW_PRESS) {
                    posChange.sub(right.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_S) == GLFW_PRESS) {
                    posChange.sub(fwd.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_D) == GLFW_PRESS) {
                    posChange.add(right.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_SPACE) == GLFW_PRESS) {
                    posChange.add(up.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_LEFT_SHIFT) == GLFW_PRESS) {
                    posChange.sub(up.mul((float) (dT * movSpeed), new Vector3f()));
                }
            }
            case WALKING: {
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_W) == GLFW_PRESS) {
                    posChange.add(fwd.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_A) == GLFW_PRESS) {
                    posChange.sub(right.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_S) == GLFW_PRESS) {
                    posChange.sub(fwd.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_D) == GLFW_PRESS) {
                    posChange.add(right.mul((float) (dT * movSpeed), new Vector3f()));
                }
                if (glfwGetKey(Main.getWindowPtr(), GLFW_KEY_SPACE) == GLFW_PRESS && collisions.get(Direction.DOWN)) {
                    velChange.add(new Vector3f(0, 0, GameManager.JUMP_VELOCITY));
                }
            }
        }
    }

    public void reset() {
        int[] nX = new int[1], nY = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), nX, nY);
        glfwSetCursorPos(Main.getWindowPtr(), nX[0] / 2.0, nY[0] / 2.0);
    }

    public Vector3f getCamPos() {
        return getPos().add(0, 0, 1.5f);
    }

    public Vector3f getCamDir() {
        return getDir();
    }

    public Vector3f getVeloctiy() {
        return getVel();
    }

    public Vector3i getCurrentChunk() {
        return new Vector3i(
                Math.floorDiv((int) Math.floor(getCamPos().x()), 16),
                Math.floorDiv((int) Math.floor(getCamPos().y()), 16),
                Math.floorDiv((int) Math.floor(getCamPos().z()), 16)
        );
    }

    public Matrix4f getViewMatrix() {
        return null;
    }

    public Matrix4f getProjViewMatrix(Matrix4f projMatrix) {
        int[] winW = new int[1], winH = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), winW, winH);

        Vector3f dir = new Vector3f(
                (float) (Math.cos(getVertical()) * Math.cos(getHorizontal())),
                (float) (Math.cos(getVertical()) * Math.sin(getHorizontal())),
                (float) Math.sin(getVertical())
        );

        Vector3f up = new Vector3f(
                (float) Math.cos(getHorizontal() - Math.PI / 2),
                (float) Math.sin(getHorizontal() - Math.PI / 2),
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

        Vector3f dir = new Vector3f(
                (float) (Math.cos(getVertical()) * Math.cos(getHorizontal())),
                (float) (Math.cos(getVertical()) * Math.sin(getHorizontal())),
                (float) Math.sin(getVertical())
        );

        Vector3f up = new Vector3f(
                (float) Math.cos(getHorizontal() - Math.PI / 2),
                (float) Math.sin(getHorizontal() - Math.PI / 2),
                (float) 0
        ).cross(dir);

        return new Matrix4f()
                .perspective((float)Math.PI/2, (float) winW[0] / winH[0], 0.1f, 100f)
                .lookAt(
                        getCamPos(),
                        getCamPos().add(dir),
                        up
                );
    }

    @Override
    public void update() {
        super.update();
        if (blockBreak.check()) {
            Vector3i block = Ray.findFirstBlock(this, server, 4);
            if (block != null) {
                server.setBlock(block, new Block());
            }
        }
        if (blockPlace.check()) {
            Vector3i block = Ray.beforeFirstBlock(this, server, 4);
            if (block != null) {
                server.setBlock(block, new Block("vanilla::bricks"));
            }
        }
    }

    @Override
    protected void onDeath() {
        setVel(new Vector3f());
        setPos(new Vector3f(server.getSpawnPoint()));
    }
}
