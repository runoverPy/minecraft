package game.core.console.userfs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class File {
    private final FilePermissions permissions;

    public File(User user, Group group) {
        permissions = new FilePermissions(user, group, (short) 00777);

    }

    public SeekableByteChannel getBinary() {

        return new SeekableByteChannel() {
            boolean closed = false;

            @Override
            public int read(ByteBuffer dst) throws IOException {
                return 0;
            }

            @Override
            public int write(ByteBuffer src) throws IOException {
                return 0;
            }

            @Override
            public long position() throws IOException {
                return 0;
            }

            @Override
            public SeekableByteChannel position(long newPosition) throws IOException {
                return null;
            }

            @Override
            public long size() throws IOException {
                return 0;
            }

            @Override
            public SeekableByteChannel truncate(long size) throws IOException {
                return null;
            }

            @Override
            public boolean isOpen() {
                return !closed;
            }

            @Override
            public void close() throws IOException {
                closed = true;
            }
        };
    }
}
