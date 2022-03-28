package game.mechanics.entities;

import game.mechanics.blocks.Phase;
import game.mechanics.collision.Direction;
import game.mechanics.collision.Hitbox;
import game.core.server.core.Server;
import game.core.GameManager;
import org.joml.RoundingMode;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.EnumMap;

public abstract class Entity implements Drawable {
    private final Vector3f pos, vel;
    protected final Server server;
    private double horizontal, vertical;
    private boolean clipGuard = true;

    protected final float width, depth, height;

    protected long lT;
    public static final int KILL_DEPTH = -128;

    public Entity(Server server, Vector3f pos, float horizontal, float vertical, float width, float height, float depth) {
        this.server = server;
        this.pos = pos;
        this.vel = new Vector3f();
        this.horizontal = horizontal;
        this.vertical = vertical;

        this.width = width;
        this.height = height;
        this.depth = depth;
        this.lT = System.currentTimeMillis();
    }

    public Entity(Server server, Vector3f pos, float width, float height, float depth) {
        this(server, pos, 0, 0, width, height, depth);
    }

    /**
     * checks if two entities are colliding, i.e. if their hitboxes overlap.
     * @param entity the entity to be compared with
     * @return whether the two objects are colliding
     */
    protected boolean isColliding(Entity entity) {
        return false;
    }


    /**
     * checks if this entity is colliding with the world
     */
    protected EnumMap<Direction, Boolean> getWorldCollisions() {
        Vector3f[] corners = new Vector3f[] {
                new Vector3f(pos.x - width/2, pos.y - depth/2, pos.z),
                new Vector3f(pos.x + width/2, pos.y - depth/2, pos.z),
                new Vector3f(pos.x - width/2, pos.y + depth/2, pos.z),
                new Vector3f(pos.x + width/2, pos.y + depth/2, pos.z),
                new Vector3f(pos.x - width/2, pos.y - depth/2, pos.z + height),
                new Vector3f(pos.x + width/2, pos.y - depth/2, pos.z + height),
                new Vector3f(pos.x - width/2, pos.y + depth/2, pos.z + height),
                new Vector3f(pos.x + width/2, pos.y + depth/2, pos.z + height),
        };


        EnumMap<Direction, Boolean> out = new EnumMap<>(Direction.class);
        for (Vector3f block : new Vector3f[] {
                new Vector3f(pos.x - width / 2, pos.y - depth / 2, pos.z + height),
                new Vector3f(pos.x + width / 2, pos.y - depth / 2, pos.z + height),
                new Vector3f(pos.x - width / 2, pos.y + depth / 2, pos.z + height),
                new Vector3f(pos.x + width / 2, pos.y + depth / 2, pos.z + height)
        }) {
            out.put(Direction.UP, server.getBlock(block.get(RoundingMode.FLOOR, new Vector3i())).getPhase() == Phase.SOLID);
            if (out.get(Direction.UP)) {
                break;
            }
        }
        for (Vector3f block : new Vector3f[] {
                new Vector3f(pos.x - width / 2, pos.y - depth / 2, (float) Math.ceil(pos.z - 1)),
                new Vector3f(pos.x + width / 2, pos.y - depth / 2, (float) Math.ceil(pos.z - 1)),
                new Vector3f(pos.x - width / 2, pos.y + depth / 2, (float) Math.ceil(pos.z - 1)),
                new Vector3f(pos.x + width / 2, pos.y + depth / 2, (float) Math.ceil(pos.z - 1))
        }) {
            out.put(Direction.DOWN, server.getBlock(block.get(RoundingMode.FLOOR, new Vector3i())).getPhase() == Phase.SOLID);
            if (out.get(Direction.DOWN)) break;
        }
        for (Vector3f block : new Vector3f[] {
                new Vector3f(pos.x + width / 2, pos.y - depth / 2, pos.z),
                new Vector3f(pos.x + width / 2, pos.y + depth / 2, pos.z),
                new Vector3f(pos.x + width / 2, pos.y - depth / 2, pos.z + height),
                new Vector3f(pos.x + width / 2, pos.y + depth / 2, pos.z + height)
        }) {
            out.put(Direction.NORTH, server.getBlock(block.get(RoundingMode.FLOOR, new Vector3i())).getPhase() == Phase.SOLID);
            if (out.get(Direction.NORTH)) break;
        }
        return out;
    }

    public void update() {
        long dT, nT;
        nT = System.currentTimeMillis();
        dT = nT - lT;
        lT = nT;

        EnumMap<Direction, Boolean> collisions = getWorldCollisions();

        // the subclass specified changes to position and velocity
        Vector3f posChange = new Vector3f(), velChange = new Vector3f();
        move(posChange, velChange, collisions, dT);

        addVel(velChange);
        addVel(new Vector3f(0, 0, (float) -GameManager.GRAV_ACCEL * (float) dT / 1000));

        posChange.add(getVel().mul((float) dT / 1000, new Vector3f()));

        if (clipGuard) {
            if (collisions.get(Direction.UP)) {
                pos.z = (float) Math.floor(pos.z - height) + height;
                if (vel.z > 0) vel.z = 0;
                if (posChange.z > 0) posChange.z = 0;
            }
            if (collisions.get(Direction.DOWN)) {
                pos.z = (float) Math.ceil(pos.z);
                if (vel.z < 0) vel.z = 0;
                if (posChange.z < 0) posChange.z = 0;
            }
//            if (collisions.get(Direction.NORTH)) {
//                pos.x = (float) Math.floor(pos.x - width / 2) + width / 2;
//                if (vel.x > 0) vel.x = 0;
//                if (posChange.x > 0) posChange.x = 0;
//            }
        }
        addPos(posChange);


        if (pos.z < KILL_DEPTH) onDeath();
    }

    protected abstract void move(Vector3f posChange, Vector3f velChange, EnumMap<Direction, Boolean> collisions, long dT);

    protected void onDeath() {
        server.removeEntity(this);
    }

    protected abstract Hitbox getHitbox();


    protected Vector3f getPos() {
        return new Vector3f(pos);
    }

    protected void addPos(Vector3f pos) {
        this.pos.add(pos);
    }

    protected void subPos(Vector3f pos) {
        this.pos.sub(pos);
    }

    protected void setPos(Vector3f pos) {
        this.pos.set(pos);
    }

    protected Vector3f getVel() {
        return new Vector3f(vel);
    }

    protected void addVel(Vector3f vel) {
        this.vel.add(vel);
    }

    protected void subVel(Vector3f vel) {
        this.vel.sub(vel);
    }

    protected void setVel(Vector3f vel) {
        this.vel.set(vel);
    }

    protected void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    protected void addHorizontal(double horizontal) {
        this.horizontal += horizontal;
    }

    public double getHorizontal() {
        return horizontal;
    }

    protected void setVertical(double vertical) {
        this.vertical = vertical;
    }

    protected void addVertical(double vertical) {
        this.vertical += vertical;
    }

    public double getVertical() {
        return vertical;
    }

    public Vector3f getDir() {
        return new Vector3f(
                (float) (Math.cos(getVertical()) * Math.cos(getHorizontal())),
                (float) (Math.cos(getVertical()) * Math.sin(getHorizontal())),
                (float) Math.sin(getVertical())
        );
    }

    public void update(EntityUpdate update) {
        this.pos.set(update.getPos());
        this.horizontal = update.getHorizontal();
        this.vertical = update.getVertical();
    }

    public static class EntityUpdate {
        private final Vector3f pos;
        private final double horizontal, vertical;

        public EntityUpdate(Vector3f pos, double horizontal, double vertical) {
            this.pos = pos;
            this.horizontal = horizontal;
            this.vertical = vertical;
        }

        public Vector3f getPos() {
            return pos;
        }

        public double getHorizontal() {
            return horizontal;
        }

        public double getVertical() {
            return vertical;
        }
    }
}
