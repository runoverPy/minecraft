package game.mechanics.entities;

import game.core.GameRuntime;
import game.main.Main;
import game.mechanics.blocks.*;
import game.mechanics.collision.Direction;
import game.mechanics.collision.Hitbox;
import game.mechanics.input.CooldownMouseInput;
import game.core.server.Server;
import game.core.GameManager;
import game.util.Ray;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.EnumMap;


import static org.lwjgl.glfw.GLFW.*;

public class Player {
    /**
     * the current movement mode
     */
    private final MovementMode movementMode;
    private static final double rotSpeed = 2 * Math.PI / 20f, movSpeed = 5 / 1000f;
    private final CooldownMouseInput blockBreak, blockPlace;
    private final Vector3f pos, vel;
    protected final Server server;
    private double horizontal, vertical;
    private boolean clipGuard = true;
    private final Hitbox hitbox;

    protected final float width = 0.5f, depth = 0.5f, height = 1.75f;

    protected long lT;
    public static final int KILL_DEPTH = -128;

    public Player(Server server) {
        this(server, server.getSpawnPoint(), 0, 0);
    }

    public Player(Server server, Vector3f pos, float vertical, float horizontal) {
        this.movementMode = MovementMode.WALKING;
        this.server = server;
        this.pos = pos;
        this.vel = new Vector3f();
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.hitbox = new Hitbox(width, depth, height);
        this.blockBreak = new CooldownMouseInput(GLFW_MOUSE_BUTTON_LEFT, 250, true);
        this.blockPlace = new CooldownMouseInput(GLFW_MOUSE_BUTTON_RIGHT, 250, true);
        this.lT = System.currentTimeMillis();
        reset();
    }

    public void draw(Matrix4f matrixPV) {
        hitbox.draw(matrixPV, pos);
    }

    public double getVertical() {
        return vertical;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public void move(Vector3f posChange, Vector3f velChange, EnumMap<Direction, Boolean> collisions, long dT) {
        double[] curX = new double[1], curY = new double[1];
        int[] winW = new int[1], winH = new int[1];
        glfwGetCursorPos(Main.getWindowPtr(), curX, curY);
        glfwGetWindowSize(Main.getWindowPtr(), winW, winH);
        glfwSetCursorPos(Main.getWindowPtr(), winW[0] / 2.0, winH[0] / 2.0);

        horizontal += (winW[0] / 2.0 - curX[0]) * (dT / 1000f) * rotSpeed;
        vertical += (winH[0] / 2.0 - curY[0]) * (dT / 1000f) * rotSpeed;

        if (getVertical() < -Math.PI / 2) vertical = -Math.PI / 2;
        if (getVertical() > Math.PI / 2) vertical = Math.PI / 2;

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
                    velChange.add(GameManager.JUMP_VELOCITY);
                }
            }
        }
    }

    public void update() {
        long dT, nT;
        nT = System.currentTimeMillis();
        dT = nT - lT;
        lT = nT;

        // the subclass specified changes to position and velocity
        Vector3f posChange = new Vector3f(), velChange = new Vector3f();
        EnumMap<Direction, Boolean> preCollisions = getWorldCollisions(this.pos, this.pos);
        move(posChange, velChange, preCollisions, dT);

        velChange.add(new Vector3f(GameManager.GRAV_ACCEL).mul((float) dT / 1000));

        vel.add(velChange);

        posChange.add(new Vector3f(vel).mul((float) dT / 1000));

        EnumMap<Direction, Boolean> postCollisions = getWorldCollisions(this.pos, new Vector3f(pos).add(posChange));

        if (clipGuard) {
            if (postCollisions.get(Direction.NORTH)) {
                pos.x = (float) Math.ceil(pos.x + width / 2) - (width / 2);
                if (vel.x > 0) vel.x = 0;
                if (posChange.x > 0) posChange.x = 0;
            }
            if (postCollisions.get(Direction.SOUTH)) {
                pos.x = (float) Math.floor(pos.x - width / 2) + (width / 2);
                if (vel.x < 0) vel.x = 0;
                if (posChange.x < 0) posChange.x = 0;
            }
            if (postCollisions.get(Direction.WEST)) {
                pos.y = (float) Math.ceil(pos.y + depth / 2) - (depth / 2);
                if (vel.y > 0) vel.y = 0;
                if (posChange.y > 0) posChange.y = 0;
            }
            if (postCollisions.get(Direction.EAST)) {
                pos.y = (float) Math.floor(pos.y - depth / 2) + (depth / 2);
                if (vel.y < 0) vel.y = 0;
                if (posChange.y < 0) posChange.y = 0;
            }
            if (postCollisions.get(Direction.UP)) {
                pos.z = (float) Math.ceil(pos.z + height) - height;
                if (vel.z > 0) vel.z = 0;
                if (posChange.z > 0) posChange.z = 0;
            }
            if (postCollisions.get(Direction.DOWN)) {
                pos.z = (float) Math.floor(pos.z);
                if (vel.z < 0) vel.z = 0;
                if (posChange.z < 0) posChange.z = 0;
            }
        }
        pos.add(posChange);

        if (pos.z < KILL_DEPTH) onDeath();}

    public float getMinDistance(float bX, float x, float dX) {
        return Math.min(dX, bX - x);
    }

    public void reset() {
        int[] nX = new int[1], nY = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), nX, nY);
        glfwSetCursorPos(Main.getWindowPtr(), nX[0] / 2.0, nY[0] / 2.0);
        this.lT = System.currentTimeMillis();
    }

    public Vector3f getCamPos() {
        return new Vector3f(pos).add(0, 0, 1.5f);
    }

    public Vector3f getCamDir() {
        return getDir();
    }

    public Vector3f getDir() {
        return new Vector3f(
                (float) (Math.cos(getVertical()) * Math.cos(getHorizontal())),
                (float) (Math.cos(getVertical()) * Math.sin(getHorizontal())),
                (float) Math.sin(getVertical())
        );
    }

    public Vector3f getVel() {
        return new Vector3f(vel);
    }

    public Vector3i getCurrentChunk() {
        return new Vector3i(
                Math.floorDiv((int) Math.floor(getCamPos().x()), 16),
                Math.floorDiv((int) Math.floor(getCamPos().y()), 16),
                Math.floorDiv((int) Math.floor(getCamPos().z()), 16)
        );
    }

    public Matrix4f getViewMatrix(Matrix4f projMatrix) {
        int[] winW = new int[1], winH = new int[1];
        glfwGetWindowSize(Main.getWindowPtr(), winW, winH);

        Vector3f dir = getDir();
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

    public EnumMap<Direction, Boolean> getWorldCollisions(Vector3f oldPos, Vector3f newPos) {
        EnumMap<Direction, Boolean> out = new EnumMap<>(Direction.class);
        int
                north1 = (int) Math.ceil(oldPos.x + width / 2 - 1),
                south1 = (int) Math.floor(oldPos.x - width / 2),
                west1 = (int) Math.ceil(oldPos.y + depth / 2 - 1),
                east1 = (int) Math.floor(oldPos.y - depth / 2),
                top1 = (int) Math.floor(oldPos.z + height),
                bot1 = (int) Math.ceil(oldPos.z);

        int
                north2 = (int) Math.floor(newPos.x + width / 2),
                south2 = (int) Math.floor(newPos.x - width / 2),
                west2 = (int) Math.floor(newPos.y + depth / 2),
                east2 = (int) Math.floor(newPos.y - depth / 2),
                top2 = (int) Math.floor(newPos.z + height),
                bot2 = (int) Math.ceil(newPos.z - 1);

        for (Vector3i block : new Vector3i[] {
                new Vector3i(north2, east1, bot1),
                new Vector3i(north2, west1, bot1),
                new Vector3i(north2, east1, top1),
                new Vector3i(north2, west1, top1)
        }) {
            out.put(Direction.NORTH, server.getBlock(block).getPhase() == Phase.SOLID);
            if (out.get(Direction.NORTH)) break;
        }
        for (Vector3i block : new Vector3i[] {
                new Vector3i(south2, east1, bot1),
                new Vector3i(south2, west1, bot1),
                new Vector3i(south2, east1, top1),
                new Vector3i(south2, west1, top1)
        }) {
            out.put(Direction.SOUTH, server.getBlock(block).getPhase() == Phase.SOLID);
            if (out.get(Direction.SOUTH)) break;
        }
        for (Vector3i block : new Vector3i[] {
                new Vector3i(south1, west2, bot1),
                new Vector3i(north1, west2, bot1),
                new Vector3i(south1, west2, top1),
                new Vector3i(north1, west2, top1)
        }) {
            out.put(Direction.WEST, server.getBlock(block).getPhase() == Phase.SOLID);
            if (out.get(Direction.WEST)) break;
        }
        for (Vector3i block : new Vector3i[] {
                new Vector3i(south1, east2, bot1),
                new Vector3i(north1, east2, bot1),
                new Vector3i(south1, east2, top1),
                new Vector3i(north1, east2, top1)
        }) {
            out.put(Direction.EAST, server.getBlock(block).getPhase() == Phase.SOLID);
            if (out.get(Direction.EAST)) break;
        }
        for (Vector3i block : new Vector3i[] {
                new Vector3i(south1, east1, top2),
                new Vector3i(north1, east1, top2),
                new Vector3i(south1, west1, top2),
                new Vector3i(north1, west1, top2)
        }) {
            out.put(Direction.UP, server.getBlock(block).getPhase() == Phase.SOLID);
            if (out.get(Direction.UP)) break;
        }
        for (Vector3i block : new Vector3i[] {
                new Vector3i(south1, east1, bot2),
                new Vector3i(north1, east1, bot2),
                new Vector3i(south1, west1, bot2),
                new Vector3i(north1, west1, bot2)
        }) {
            out.put(Direction.DOWN, server.getBlock(block).getPhase() == Phase.SOLID);
            if (out.get(Direction.DOWN)) break;
        }
        return out;
    }

    public void update(GameManager game) {
        update();
        if (blockBreak.check()) {
            Vector3i block = Ray.findFirstBlock(this, server, 4);
            if (block != null) {
                server.setBlock(block, new Block());
            }
        }
        if (blockPlace.check()) {
            Vector3i block = Ray.beforeFirstBlock(this, server, 4);
            if (block != null) {
                String item = game.getHotbar().getItem();
                if (item != null && GameRuntime.getInstance().getBlockRegister().registered(item)) {
                    server.setBlock(block, new Block(item));
                } else System.out.println("cannot place block");
            }
        }
    }

    protected void onDeath() {
        vel.set(new Vector3f());
        pos.set(server.getSpawnPoint());
    }
}
