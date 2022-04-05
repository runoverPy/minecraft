package game.mechanics.entities;

import game.mechanics.collision.Direction;
import game.mechanics.collision.Hitbox;
import game.core.server.Server;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.EnumMap;

public class Mob extends Entity {
    public Mob(Server server, Vector3f pos, float horizontal, float vertical, float width, float height, float depth) {
        super(server, pos, horizontal, vertical, width, height, depth);
    }

    @Override
    public void draw(Matrix4f matrixPV) {

    }

    @Override
    protected void move(Vector3f posChange, Vector3f velChange, EnumMap<Direction, Boolean> collisions, long dT) {

    }

    @Override
    protected Hitbox getHitbox() {
        return null;
    }
}
