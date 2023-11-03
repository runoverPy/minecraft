package game.core.console;

import game.core.console.userfs.ConsoleFileSystemProvider1;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;

public class ShellCommandEnvironment extends CommandEnvironment {
    private static FileSystemProvider fsProvider = new ConsoleFileSystemProvider1();
    private static FileSystem internalFileSystem;
    public static Path HOME = Path.of("/");

    static {
//        try {
//            internalFileSystem = fsProvider.getFileSystem(new URI("gamefs:///"));
//            HOME = internalFileSystem.getPath("/home/eric");
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
    }

    private String userName = "user";
    private String hostName = "host";
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    private Path workingDirectory = HOME;
    public void changeDirectory(String path) {
        workingDirectory = workingDirectory.resolve(path).normalize();
    }

    public void changeDirectory(Path path) {
        workingDirectory = workingDirectory.resolve(path).normalize();
    }

    public Path getDirectory() {
        return workingDirectory;
    }
}
