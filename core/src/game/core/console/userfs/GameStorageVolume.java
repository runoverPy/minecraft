package game.core.console.userfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameStorageVolume {
    public static final int BLOCK_SIZE = 4096;
    public static final int GROUP_SIZE = 32768;

    Path storageLocation;
    ByteBuffer memory;

    public GameStorageVolume(int size) {
        memory = ByteBuffer.allocateDirect(size);
    }

    public void defragment() {
        throw new UnsupportedOperationException();
    }


    public FileChannel openFile(String[] names) {
        return null;
    }

    byte[] getBlock(int block) {
        byte[] data = new byte[BLOCK_SIZE];
        memory.get(block * BLOCK_SIZE, data);
        return data;
    }

    void setBlock(int block, byte[] data) {
        memory.put(block * BLOCK_SIZE, data);
    }

    ByteBuffer getBlockBufferSlice(int block) {
        return memory.slice(BLOCK_SIZE * block, BLOCK_SIZE);
    }

    // naive implementation of volume save / load

    public void save() throws IOException {
        ByteBuffer memory = this.memory.asReadOnlyBuffer().rewind();
        try (ByteChannel channel = Files.newByteChannel(storageLocation)) {
            channel.write(memory);
        }
    }

    public void load() throws IOException {
        memory.clear();
        try (ByteChannel channel = Files.newByteChannel(storageLocation)) {
            channel.read(memory);
        }
    }
}
