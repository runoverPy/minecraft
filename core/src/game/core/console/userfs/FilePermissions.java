package game.core.console.userfs;

public class FilePermissions {
    public static final short
      U_R = 00400,
      U_W = 00200,
      U_X = 00100,
      G_R = 00040,
      G_W = 00020,
      G_X = 00010,
      O_R = 00004,
      O_W = 00002,
      O_X = 00001;

    private User user;
    private Group group;
    private short flags;

    public FilePermissions(User user, Group group, short flags) {
        this.user = user;
        this.group = group;
        this.flags = flags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public short getFlags() {
        return flags;
    }

    public void setFlags(short flags) {
        this.flags = flags;
    }
}
