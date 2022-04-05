package game.mechanics.entities;

import game.mechanics.collision.Direction;
import game.mechanics.collision.Hitbox;
import game.core.server.Server;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.EnumMap;

public class Item extends Entity {
    private static final float[] vertices = new float[] {

    };

    public Item(Server server, Vector3f pos) {
        super(server, pos, 0, 0, 0.25f, 0.25f, 0.25f);
    }

    @Override
    public void draw(Matrix4f matrixPV) {

    }

    @Override
    protected void move(Vector3f posChange, Vector3f velChange, EnumMap<Direction, Boolean> collisions, long dT) {
        
    }

    @Override
    protected Hitbox getHitbox() {
        return new Hitbox(0.25f, 0.25f, 0.25f);
    }
}
