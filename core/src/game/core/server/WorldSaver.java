package game.core.server;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorldSaver {
    private static final Path rootSavePath;

    static {
        rootSavePath = Paths.get("worlds/");
        if (!Files.isDirectory(rootSavePath)) {
            try {
                Files.createDirectory(rootSavePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final Path gameSavePath;
    private final Path gameJsonPath;

    public WorldSaver(String gameSaveName) throws IOException {
        gameSavePath = rootSavePath.resolve(gameSaveName);
        if (!Files.isDirectory(gameSavePath))
            Files.createDirectory(gameSavePath);
        gameJsonPath = gameSavePath.resolve("world.json");
        if (!Files.isRegularFile(gameJsonPath))
            Files.createFile(gameJsonPath);
    }

    private Path getChunkSavePath(int cX, int cY, int cZ) {
        return gameSavePath.resolve(String.format("%d_%d_%d.chunk", cX, cY, cZ));
    }

    public Chunk loadChunk(int cX, int cY, int cZ) {
        if (isChunkSaved(cX, cY, cZ))
            return null;
        else {
            return null;
        }
    }

    public boolean isChunkSaved(int cX, int cY, int cZ) {
        return Files.isRegularFile(getChunkSavePath(cX, cY, cZ)); // &&
    }

    public void saveChunk(int cX, int cY, int cZ, Chunk chunk) throws IOException {
        JSONArray chunkSerial = Chunk.encode(chunk);

    }
}