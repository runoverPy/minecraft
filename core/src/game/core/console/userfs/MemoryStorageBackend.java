package game.core.console.userfs;

import java.nio.channels.SeekableByteChannel;
import java.nio.file.Path;

public interface MemoryStorageBackend<P extends Path> {
    SeekableByteChannel openByteChannel(Path path);
}
