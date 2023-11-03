package game.core.console.userfs;

import java.util.*;

/**
 * Path for the game's internal file system, largely based off of sun.nio.fs.UnixPath
 */
public class GamePath implements Comparable<GamePath>, Iterable<GamePath> {
    private final GameFileSystem fileSystem;
    private final String path;
    private final int[] offsets;

    public GamePath(GameFileSystem fileSystem, String path) {
        this.fileSystem = fileSystem;
        this.path = path;

        if (Objects.equals(path, "")) offsets = new int[] { 0 };
        else {
            int count = 0;
            int index = 0;
            while (index < path.length()) {
                char c = path.charAt(index++);
                if (c != '/') {
                    count++;
                    while (index < path.length() && path.charAt(index) != '/')
                        index++;
                }
            }


            // populate offsets
            int[] result = new int[count];
            count = 0;
            index = 0;
            while (index < path.length()) {
                char c = path.charAt(index);
                if (c == '/') {
                    index++;
                } else {
                    result[count++] = index++;
                    while (index < path.length() && path.charAt(index) != '/')
                        index++;
                }
            }
            offsets = result;
        }
    }

    public GamePath getParent() {
        return null;
    }

    public GamePath getFileName() {
        return null;
    }

    public int getNameCount() {
        return offsets.length;
    }

    public GamePath getName(int index) {
        return null;
    }

    public GamePath subPath(int beginIndex, int endIndex) {
        return null;
    }

    public boolean startsWith(GamePath other) {
        return false;
    }

    public boolean endsWith(GamePath other) {
        return false;
    }

    public GamePath normalize() {
        final int count = getNameCount();
        if (count == 0 || Objects.equals(path, ""))
            return this;

        boolean[] ignore = new boolean[count];      // true => ignore name
        int[] size = new int[count];                // length of name
        int remaining = count;                      // number of names remaining
        boolean hasDotDot = false;                  // has at least one ..
        boolean isAbsolute = isAbsolute();

        // first pass:
        //   1. compute length of names
        //   2. mark all occurrences of "." to ignore
        //   3. and look for any occurrences of ".."
        for (int i = 0; i < count; i++) {
            int begin = offsets[i];
            int len;
            if (i == (offsets.length-1)) {
                len = path.length() - begin;
            } else {
                len = offsets[i+1] - begin - 1;
            }
            size[i] = len;

            if (path.charAt(begin) == '.') {
                if (len == 1) {
                    ignore[i] = true;  // ignore  "."
                    remaining--;
                }
                else {
                    if (path.charAt(begin+1) == '.')   // ".." found
                        hasDotDot = true;
                }
            }
        }

        // multiple passes to eliminate all occurrences of name/..
        if (hasDotDot) {
            int prevRemaining;
            do {
                prevRemaining = remaining;
                int prevName = -1;
                for (int i=0; i<count; i++) {
                    if (ignore[i])
                        continue;

                    // not a ".."
                    if (size[i] != 2) {
                        prevName = i;
                        continue;
                    }

                    int begin = offsets[i];
                    if (path.charAt(begin) != '.' || path.charAt(begin+1) != '.') {
                        prevName = i;
                        continue;
                    }

                    // ".." found
                    if (prevName >= 0) {
                        // name/<ignored>/.. found so mark name and ".." to be
                        // ignored
                        ignore[prevName] = true;
                        ignore[i] = true;
                        remaining = remaining - 2;
                        prevName = -1;
                    } else {
                        // Case: /<ignored>/.. so mark ".." as ignored
                        if (isAbsolute) {
                            boolean hasPrevious = false;
                            for (int j=0; j<i; j++) {
                                if (!ignore[j]) {
                                    hasPrevious = true;
                                    break;
                                }
                            }
                            if (!hasPrevious) {
                                // all proceeding names are ignored
                                ignore[i] = true;
                                remaining--;
                            }
                        }
                    }
                }
            } while (prevRemaining > remaining);
        }

        // no redundant names
        if (remaining == count)
            return this;

        // corner case - all names removed
        if (remaining == 0) {
            return isAbsolute ? fileSystem.rootDirectory() : new GamePath(fileSystem, "");
        }

        // compute length of result
        int len = remaining - 1;
        if (isAbsolute)
            len++;

        for (int i = 0; i < count; i++) {
            if (!ignore[i])
                len += size[i];
        }

        // copy names into result
        byte[] result = new byte[len];
        int pos = 0;
        if (isAbsolute) {
            result[pos++] = '/';
        }
        for (int i = 0; i < count; i++) {
            if (!ignore[i]) {
                System.arraycopy(path.getBytes(), offsets[i], result, pos, size[i]);
                pos += size[i];
                if (--remaining > 0) {
                    result[pos++] = '/';
                }
            }
        }
        return new GamePath(fileSystem, new String(result));
    }

    public GamePath resolve(GamePath other) {
        if (other.path.length() > 0 && other.path.charAt(0) == '/')
            return other;
        String result = resolve(path, other.path);
        return new GamePath(fileSystem, result);
    }

    private static String resolve(String base, String child) {
        if (Objects.equals(child, "")) return base;
        if (Objects.equals(base, "") || child.charAt(0) == '/') return child;
        StringBuilder builder;
        if (Objects.equals(base, "/")) {
            builder = new StringBuilder(1 + child.length());
            builder.append('/').append(child);
        } else {
            builder = new StringBuilder(base.length() + 1 + child.length());
            builder.append(base).append('/').append(child);
        }
        return builder.toString();
    }

    private boolean hasDotOrDotDot() {
        int n = getNameCount();
        for (int i=0; i<n; i++) {
            byte[] bytes = getName(i).path.getBytes();
            if ((bytes.length == 1 && bytes[0] == '.'))
                return true;
            if ((bytes.length == 2 && bytes[0] == '.') && bytes[1] == '.') {
                return true;
            }
        }
        return false;
    }

    public GamePath relativize(GamePath child) {
        if (child.equals(this))
            return new GamePath(fileSystem, "");

        // can only relativize paths of the same type
        if (this.isAbsolute() != child.isAbsolute())
            throw new IllegalArgumentException("'other' is different type of Path");

        // this path is the empty path
        if (Objects.equals(this.path, ""))
            return child;

        GamePath base = this;
        if (base.hasDotOrDotDot() || child.hasDotOrDotDot()) {
            base = base.normalize();
            child = child.normalize();
        }

        int baseCount = base.getNameCount();
        int childCount = child.getNameCount();

        // skip matching names
        int n = Math.min(baseCount, childCount);
        int i = 0;
        while (i < n) {
            if (!base.getName(i).equals(child.getName(i)))
                break;
            i++;
        }

        // remaining elements in child
        GamePath childRemaining;
        boolean isChildEmpty;
        if (i == childCount) {
            childRemaining = new GamePath(fileSystem, "");
            isChildEmpty = true;
        } else {
            childRemaining = child.subPath(i, childCount);
            isChildEmpty = Objects.equals(child.path, "");
        }

        // matched all of base
        if (i == baseCount) {
            return childRemaining;
        }

        // the remainder of base cannot contain ".."
        GamePath baseRemaining = base.subPath(i, baseCount);
        if (baseRemaining.hasDotOrDotDot()) {
            throw new IllegalArgumentException("Unable to compute relative "
              + " path from " + this + " to " + child);
        }
        if (Objects.equals(baseRemaining.path, ""))
            return childRemaining;

        // number of ".." needed
        int dotdots = baseRemaining.getNameCount();
        if (dotdots == 0) {
            return childRemaining;
        }

        // result is a  "../" for each remaining name in base followed by the
        // remaining names in child. If the remainder is the empty path
        // then we don't add the final trailing slash.
        int len = dotdots*3 + childRemaining.path.length();
        if (isChildEmpty) {
            assert Objects.equals(childRemaining.path, "");
            len--;
        }
        byte[] result = new byte[len];
        int pos = 0;
        while (dotdots > 0) {
            result[pos++] = (byte)'.';
            result[pos++] = (byte)'.';
            if (isChildEmpty) {
                if (dotdots > 1) result[pos++] = (byte)'/';
            } else {
                result[pos++] = (byte)'/';
            }
            dotdots--;
        }
        System.arraycopy(childRemaining.path.getBytes(),0, result, pos, childRemaining.path.length());
        return new GamePath(fileSystem, new String(result));
    }

    public GamePath toAbsolutePath() {
        if (isAbsolute()) return this;
        return fileSystem.defaultDirectory().resolve(this);
    }

    public boolean isAbsolute() {
        return path.length() > 0 && path.charAt(0) == '/';
    }

    @Override
    public int compareTo(GamePath that) {
        int len1 = this.path.length();
        int len2 = that.path.length();

        int n = Math.min(len1, len2);

        int k = 0;
        while (k < n) {
            int c1 = this.path.charAt(k);
            int c2 = that.path.charAt(k);
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

    @Override
    public Iterator<GamePath> iterator() {
        return new Iterator<>() {
            int index;

            @Override
            public boolean hasNext() {
                return index < getNameCount();
            }

            @Override
            public GamePath next() {
                if (index < getNameCount()) {
                    return getName(index++);
                }
                throw new NoSuchElementException();
            }
        };
    }
}
