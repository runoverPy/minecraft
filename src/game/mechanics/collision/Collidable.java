package game.mechanics.collision;

import java.util.Map;

public interface Collidable {
    Map<Direction, Boolean> getCollisions(Collidable other);
}
