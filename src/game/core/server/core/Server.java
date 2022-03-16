package game.core.server.core;

import game.mechanics.blocks.Block;
import game.mechanics.entities.Entity;
import org.joml.Vector3i;

import java.io.Closeable;
import java.io.IOException;

/**
 * The side of the client-server connection that is exposed to the game
 */
public interface Server extends Closeable {
    Block getBlock(int x, int y, int z);
    void setBlock(int x, int y, int z, Block block);
    void updateBlock(int x, int y, int z);

    default Block getBlock(Vector3i block) {return getBlock(block.x(), block.y(), block.z());}
    default void setBlock(Vector3i block, Block newBlock) {setBlock(block.x(), block.y(), block.z(), newBlock);}
    default void updateBlock(Vector3i block) {updateBlock(block.x(), block.y(), block.z());}

    void registerEntity(Entity entity);
    void removeEntity(Entity entity);

    Vector3i getSpawnPoint();

    void update();

    void registerClient(Client client);
    void disconnectClient(Client client) throws IOException;

    Chunk getChunk(int cX, int cY, int cZ);
    default Chunk getChunk(Vector3i chunkPos) {return getChunk(chunkPos.x(), chunkPos.y(), chunkPos.z());}
}