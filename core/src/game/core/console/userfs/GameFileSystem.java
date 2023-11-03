package game.core.console.userfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GameFileSystem {
    private final FSDir root;

    public GameFileSystem() {
        root = new FSDir();
    }

    public FSNode getNode(Path path) throws FileNotFoundException {
        path = path.normalize();
        FSNode node = root;
        for (Path pathStep : path) {
            if (!(node instanceof FSDir dir) || (node = dir.getChild(pathStep.toString())) == null) {
                throw new FileNotFoundException();
            }
        }
        return node;
    }

    public void createDirectory(GamePath path) {

    }

    public void createFile(GamePath path) {

    }

    public GamePath defaultDirectory() {
        return null;
    }

    public GamePath rootDirectory() {
        return new GamePath(this, "/");
    }

    public void close() throws IOException {}


    public boolean isOpen() {
        return false;
    }

    public String getSeparator() {
        return "/";
    }

    public Iterable<GamePath> getRootDirectories() {
        return null;
    }

    public Iterable<GameFileStore> getFileStores() {
        return null;
    }

    public GamePath getPath(String first, String... more) {
        if (first == null) throw new NullPointerException();
        String path;
        if (more.length == 0) path = first;
        else {
            StringBuilder builder = new StringBuilder();
            builder.append(first);
            for (String next : more) {
                if (!next.isEmpty()) {
                    if (!builder.isEmpty())
                        builder.append('/');
                    builder.append(next);
                }
            }
            path = builder.toString();
        }
        return new GamePath(this, path);
    }

    public GamePathMatcher getPathMatcher(String syntaxAndPattern) {
        int pos = syntaxAndPattern.indexOf(':');
        if (pos <= 0)
            throw new IllegalArgumentException();
        String syntax = syntaxAndPattern.substring(0, pos);
        String input = syntaxAndPattern.substring(pos+1);

        String expr;
        if (syntax.equalsIgnoreCase("glob")) {
            expr = Globs.toUnixRegexPattern(input);
        } else if (syntax.equalsIgnoreCase("regex")) {
            expr = input;
        } else {
            throw new UnsupportedOperationException("Syntax '" + syntax +
              "' not recognized");
        }


        // return matcher
        final Pattern pattern = Pattern.compile(expr);

        return path -> pattern.matcher(path.toString()).matches();
    }

    private class FSNode {

    }

    private class FSFile extends FSNode {

    }

    private class FSDir extends FSNode {
        private final Map<String, FSNode> children;

        public FSDir() {
            children = new HashMap<>();
        }

        public FSNode getChild(String child) {
            return children.get(child);
        }
    }
}
