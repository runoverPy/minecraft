package game.mechanics.entities;

import game.core.GameManager;
import game.mechanics.collision.Direction;
import game.mechanics.collision.Hitbox;
import game.core.server.Server;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.EnumMap;

/**
 * A class defining players controlled remotely
 */
public abstract class Puppet extends Entity {

    public Puppet(Server server, Vector3f pos, float horizontal, float vertical) {
        super(server, pos, horizontal, vertical, 0.5f, 1.75f, 0.5f);
    }

    public Puppet(Server server, Vector3f pos) {
        this(server, pos, 0, 0);
    }

    @Override
    public void draw(Matrix4f matrixPV) {

    }

    /**
     * convert instructions from server to movement
     * @param posChange
     * @param velChange
     * @param collisions
     * @param dT
     */
    @Override
    protected void move(Vector3f posChange, Vector3f velChange, EnumMap<Direction, Boolean> collisions, long dT) {

    }

    @Override
    protected Hitbox getHitbox() {
        return new Hitbox(0.5f, 1.75f, 0.5f);
    }

    public abstract void update(GameManager game);
}
