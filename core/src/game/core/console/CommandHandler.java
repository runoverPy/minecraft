package game.core.console;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandHandler<Env extends CommandEnvironment> {
    private final CommandLibrary library;
    private final List<CommandEnvironment> environmentList;
    protected final Env env;

    public CommandHandler(CommandLibrary library, Env env) {
        this.library = library;
        environmentList = new ArrayList<>();
        this.env = env;
    }

    public CommandLibrary getLibrary() {
        return library;
    }

    public Env getEnv() {
        return env;
    }

    public abstract void execCommand(String command);
}
