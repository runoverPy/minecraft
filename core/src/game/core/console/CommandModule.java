package game.core.console;

public abstract class CommandModule<Env extends CommandEnvironment> {
    private final Env cmdEnv;

    public CommandModule(Env cmdEnv) {
        this.cmdEnv = cmdEnv;
    }

    public Env getCmdEnv() {
        return cmdEnv;
    }

    public abstract void loadCommands(CommandLibrary library);
}
