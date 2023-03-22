package game.core.rendering;

import game.util.Side;
import org.joml.Vector3i;

import java.util.Objects;

public class Face {
    private final Vector3i pos;
    private final Side side;

    public Face(Vector3i pos, Side side) {
        this.pos = pos;
        this.side = side;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Face other = (Face) obj;
        return this.pos.equals(other.pos) && this.side == other.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, side);
    }

    public Vector3i getPosition() {
        return pos;
    }

    public Side getSide() {
        return side;
    }
}
