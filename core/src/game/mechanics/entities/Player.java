package game.mechanics.entities;

//import game.assets.ui_elements.Hotbar;

import game.assets.mcui.export.Hotbar;
import game.core.GameRuntime;
import game.core.server.Block;
import game.core.server.Server;
import game.main.Main;
import game.mechanics.collision.Direction;
import game.mechanics.collision.Hitbox;
import game.mechanics.input.CooldownMouseInput;
import game.mechanics.physics.Physics;
import game.util.Ray;
import game.window.GLFWWindow;
import game.window.KeyCallback;
import game.window.ScrollCallback;
import mdk.blocks.Phase;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.EnumMap;

import static org.lwjgl.glfw.GLFW.*;

public class Player {
    /**
     * the current movement mode
     */
    private MovementMode movementMode;
    private final ModeSwitcher switcher = new ModeSwitcher();
    private static final float rotSpeed = (float) (2 * Math.PI / 20f), movSpeed = 3.7f / 1000;
    private final CooldownMouseInput blockBreak, blockPlace;
    private final KeyCallback hotbarCallback;
    private final ScrollCallback scrollCallback;

    private final Vector3f pos, vel;
    protected final Server server;
    private double horizontal, vertical;
    private boolean clipGuard = true;
    private final Hitbox hitbox;
    private final Hotbar hotbar;

    protected final float width = 0.5f, depth = 0.5f, height = 1.75f;

    protected long lT, lastJumpTime;
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
        this.hotbar = Hotbar.demoHotbar();
        this.blockBreak = new CooldownMouseInput(GLFW_MOUSE_BUTTON_LEFT, 250, true);
        this.blockPlace = new CooldownMouseInput(GLFW_MOUSE_BUTTON_RIGHT, 250, true);
        this.lT = System.currentTimeMillis();
        reset();

        hotbarCallback = (key, scancode, action, mods) -> {
            if ('0' <= key && key <= '9') {
                hotbar.select(key - 49);
            }
        };

        Main.getActiveWindow().addKeyCallback(hotbarCallback);

        scrollCallback = (xOffset, yOffset) -> {
            if (yOffset > 0) {
                hotbar.incSelect();
            } else if (yOffset < 0) {
                hotbar.decSelect();
            }
        };

        Main.getActiveWindow().addScrollCallback(scrollCallback);
    }

    public void draw(Matrix4f matrixPV) {
//        hitbox.draw(matrixPV, pos);
    }

    public double getVertical() {
        return vertical;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public void reset() {
        GLFWWindow.Dimension windowSize = Main.getActiveWindow().getWindowSize();
        Main.getActiveWindow().setCursorPos(windowSize.width() / 2.0, windowSize.height() / 2.0);
        this.lT = System.currentTimeMillis();
    }

    public Vector3f getPos() {
        return new Vector3f(pos);
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

    public boolean collides(Vector3i block) {
        return  pos.x - width / 2 < block.x + 1 && block.x < pos.x + width / 2 &&
                pos.y - depth / 2 < block.y + 1 && block.y < pos.y + depth / 2 &&
                pos.z < block.z + 1 && block.z < pos.z + height;
    }

    private boolean getWorldCollision(Direction direction, Vector3f oldPos, Vector3f newPos) {
        int
          north1 = (int) Math.ceil(oldPos.x + width / 2 - 1),
          south1 = (int) Math.floor(oldPos.x - width / 2),
          west1 = (int) Math.ceil(oldPos.y + depth / 2 - 1),
          east1 = (int) Math.floor(oldPos.y - depth / 2),
          top1 = (int) Math.ceil(oldPos.z + height - 1),
          bot1 = (int) Math.floor(oldPos.z);

        int
          north2 = (int) Math.floor(newPos.x + width / 2),
          south2 = (int) Math.floor(newPos.x - width / 2),
          west2 = (int) Math.floor(newPos.y + depth / 2),
          east2 = (int) Math.floor(newPos.y - depth / 2),
          top2 = (int) Math.floor(newPos.z + height),
          bot2 = (int) Math.ceil(newPos.z - 1); // 1


        return switch (direction) {
            case NORTH -> {
                for (int y = east1; y <= west1; y++) {
                    for (int z = bot1; z <= top1; z++) {
                        if (server.getBlock(north2, y, z).getPhase() == Phase.SOLID) yield true;
                    }
                }
                yield false;
            }
            case SOUTH -> {
                for (int y = east1; y <= west1; y++) {
                    for (int z = bot1; z <= top1; z++) {
                        if (server.getBlock(south2, y, z).getPhase() == Phase.SOLID) yield true;
                    }
                }
                yield false;
            }
            case WEST -> {
                for (int x = south1; x <= north1; x++) {
                    for (int z = bot1; z <= top1; z++) {
                        if (server.getBlock(x, west2, z).getPhase() == Phase.SOLID) yield true;
                    }
                }
                yield false;
            }
            case EAST -> {
                for (int x = south1; x <= north1; x++) {
                    for (int z = bot1; z <= top1; z++) {
                        if (server.getBlock(x, east2, z).getPhase() == Phase.SOLID) yield true;
                    }
                }
                yield false;
            }
            case UP -> {
                for (int x = south1; x <= north1; x++) {
                    for (int y = east1; y <= west1; y++) {
                        if (server.getBlock(x, y, top2).getPhase() == Phase.SOLID) yield true;
                    }
                }
                yield false;
            }
            case DOWN -> {
                for (int x = south1; x <= north1; x++) {
                    for (int y = east1; y <= west1; y++) {
                        if (server.getBlock(x, y, bot2).getPhase() == Phase.SOLID) yield true;
                    }
                }
                yield false;
            }
            case FILL -> {
                for (int x = south1; x <= north1; x++) {
                    for (int y = east1; y <= west1; y++) {
                        for (int z = bot1; z <= top1; z++) {
                            if (server.getBlock(x, y, z).getPhase() == Phase.SOLID) yield true;
                        }
                    }
                }
                yield false;
            }
        };
    }

    public void move(Vector3f posChange, Vector3f velChange, long dT) {
        GLFWWindow.Dimension windowSize = Main.getActiveWindow().getWindowSize();
        GLFWWindow.Position<Double> cursorPos = Main.getActiveWindow().getCursorPos();
        Main.getActiveWindow().setCursorPos(windowSize.width() / 2.0, windowSize.height() / 2.0);

        float rotSpeed = Main.getSettings().getDPI() * Player.rotSpeed;

        horizontal += (windowSize.width() / 2.0 - cursorPos.x()) * (dT / 1000f) * rotSpeed;
        vertical += (windowSize.height() / 2.0 - cursorPos.y()) * (dT / 1000f) * rotSpeed;

        if (getVertical() < -Math.PI / 2) vertical = -Math.PI / 2;
        if (getVertical() > Math.PI / 2) vertical = Math.PI / 2;

        if (getWorldCollision(Direction.FILL, pos, pos)) return;

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
            case FLYING -> {
                if (Main.getActiveWindow().getKey(GLFW_KEY_W) == GLFW_PRESS) {
                    posChange.add(fwd.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_A) == GLFW_PRESS) {
                    posChange.sub(right.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_S) == GLFW_PRESS) {
                    posChange.sub(fwd.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_D) == GLFW_PRESS) {
                    posChange.add(right.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_SPACE) == GLFW_PRESS) {
                    posChange.add(up.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_LEFT_SHIFT) == GLFW_PRESS) {
                    posChange.sub(up.mul(dT * movSpeed, new Vector3f()));
                }
            }
            case WALKING, SNEAKING -> {
                if (Main.getActiveWindow().getKey(GLFW_KEY_W) == GLFW_PRESS) {
                    posChange.add(fwd.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_A) == GLFW_PRESS) {
                    posChange.sub(right.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_S) == GLFW_PRESS) {
                    posChange.sub(fwd.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_D) == GLFW_PRESS) {
                    posChange.add(right.mul(dT * movSpeed, new Vector3f()));
                }
                if (Main.getActiveWindow().getKey(GLFW_KEY_SPACE) == GLFW_PRESS && getWorldCollision(Direction.DOWN, pos, pos)) {
                    velChange.add(Physics.JUMP_VELOCITY);
                }
            }
        }
    }

    public void update() {
        long dT, nT;
        nT = System.currentTimeMillis();
        dT = nT - lT;
        lT = nT;

        switcher.update();

        // the subclass specified changes to position and velocity
        Vector3f posChange = new Vector3f(), velChange = new Vector3f();
        move(posChange, velChange, dT);

        if (movementMode != MovementMode.FLYING)
            velChange.add(new Vector3f(Physics.GRAV_ACCEL).mul((float) dT / 1000));

        vel.add(velChange);

        posChange.add(new Vector3f(vel).mul((float) dT / 1000));

        int steps = (int) Math.ceil(posChange.length() / 0.01);
        Vector3f posStep = posChange.div(steps, new Vector3f());

        for (int i = 0; i < steps; i++) {
            Vector3f nextPos = new Vector3f(pos).add(posStep);
            if (clipGuard) {
                if (getWorldCollision(Direction.NORTH, this.pos, nextPos)) {
                    if (vel.x > 0) vel.x = 0;
                    if (posStep.x > 0) posStep.x = (int) Math.floor(nextPos.x + width) - (pos.x + width / 2);
                }
                if (getWorldCollision(Direction.SOUTH, this.pos, nextPos)) {
                    if (vel.x < 0) vel.x = 0;
                    if (posStep.x < 0) posStep.x = (int) Math.ceil(nextPos.x - width) - (pos.x - width / 2);
                }
                if (getWorldCollision(Direction.WEST, this.pos, nextPos)) {
                    if (vel.y > 0) vel.y = 0;
                    if (posStep.y > 0) posStep.y = (int) Math.floor(nextPos.y + depth) - (pos.y + depth / 2);
                }
                if (getWorldCollision(Direction.EAST, this.pos, nextPos)) {
                    if (vel.y < 0) vel.y = 0;
                    if (posStep.y < 0) posStep.y = (int) Math.ceil(nextPos.y - depth) - (pos.y - depth / 2);
                }
                if (getWorldCollision(Direction.UP, this.pos, nextPos)) {
                    if (vel.z > 0) vel.z = 0;
                    if (posStep.z > 0) posStep.z = (int) Math.floor(nextPos.z + height) - (pos.z + height);
                }
                if (getWorldCollision(Direction.DOWN, this.pos, nextPos)) {
                    if (vel.z < 0) vel.z = 0;
                    if (posStep.z < 0) posStep.z = (int) Math.ceil(nextPos.z) - pos.z;
                }
            }
            pos.add(posStep);
        }

        if (pos.z < KILL_DEPTH) onDeath();


        if (blockBreak.check()) {
            Vector3i block = Ray.findFirstBlock(this, server, 4);
            if (block != null) {
                server.setBlock(block, new Block());
            }
        }
        if (blockPlace.check()) {
            Vector3i block = Ray.beforeFirstBlock(this, server, 4);
            if (block != null && !collides(block)) {
                String item = hotbar.getItem();
                if (item != null && GameRuntime.getInstance().getBlockRegister().isLoaded(item)) {
                    server.setBlock(block, new Block(item));
                } else System.out.println("cannot place block, because it is not loaded");
            }
        }
    }

    protected void onDeath() {
        vel.set(new Vector3f());
        pos.set(server.getSpawnPoint());
    }

    public Hotbar getHotbar() {
        return hotbar;
    }

    private class ModeSwitcher {
        Long lastJumpTime = null;
        long jumpCoolDown = 250;
        boolean jumped, switched;

        public void update() {
            if (movementMode == MovementMode.FLYING && getWorldCollision(Direction.DOWN, pos, pos)) {
                movementMode = MovementMode.WALKING;
            }
            if (Main.getActiveWindow().getKey(GLFW_KEY_SPACE) == GLFW_PRESS && !jumped) {
                long thisJumpTime = System.currentTimeMillis();
                if (!switched) {
                    if (lastJumpTime != null && thisJumpTime - lastJumpTime < jumpCoolDown) {
                        movementMode = switch (movementMode) {
                            case WALKING, SNEAKING -> MovementMode.FLYING;
                            case FLYING -> MovementMode.WALKING;
                            case SWIMMING -> MovementMode.SWIMMING;
                        };
                        vel.z = 0;
                        switched = true;
                    }
                } else {
                    switched = false;
                }
                jumped = true;
                lastJumpTime = thisJumpTime;
            }
            if (Main.getActiveWindow().getKey(GLFW_KEY_SPACE) == GLFW_RELEASE && jumped) {
                jumped = false;
            }
        }
    }

    public void close() {
        Main.getActiveWindow().delKeyCallback(hotbarCallback);
        Main.getActiveWindow().delScrollCallback(scrollCallback);
    }
}
