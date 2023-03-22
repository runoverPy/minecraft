package game.util;

import mdk.blocks.Phase;
import game.mechanics.entities.Player;
import game.core.server.Server;
import org.joml.RoundingMode;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class Ray {
    public static Vector3i findFirstBlock(Vector3f center, Vector3f direction, Server server, float maxDist) {
        float inc = 0.001f;
        Vector3i block;
        for (float dist = 0; dist < maxDist; dist += inc) {
            block = direction.mul(dist, new Vector3f()).add(center).get(RoundingMode.FLOOR, new Vector3i());
            if (server.getBlock(block).getPhase() != Phase.GAS) return block;
        }
        return null;
    }

    public static Vector3i findFirstBlock(Player player, Server server, float dist) {
        return findFirstBlock(player.getCamPos(), player.getCamDir(), server, dist);
    }

    public static Vector3i beforeFirstBlock(Vector3f center, Vector3f direction, Server server, float maxDist) {
        float inc = 0.001f;
        Vector3i block = null, nextBlock;
        for (float dist = 0; dist < maxDist; dist += inc) {
            nextBlock = direction.mul(dist, new Vector3f()).add(center).get(RoundingMode.FLOOR, new Vector3i());
            if (server.getBlock(nextBlock).getPhase() != Phase.GAS) return block;
            block = nextBlock;
        }
        return null;
    }

    public static Vector3i beforeFirstBlock(Player player, Server server, float dist) {
        return beforeFirstBlock(player.getCamPos(), player.getCamDir(), server, dist);
    }
}