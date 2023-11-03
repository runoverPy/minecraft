package game.core.console.userfs;

import java.nio.ByteBuffer;

public class HybridStorageVolume {
    private final ByteBuffer memory;

    public HybridStorageVolume(int size, int blockSize) {
        this.memory = ByteBuffer.allocateDirect((int) (Math.ceil((double) size / blockSize) * blockSize));
    }

    public Object allocBlock() {
        return null;
    }

    public void deallocBlock() {}
}
