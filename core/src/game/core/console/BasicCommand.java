package game.core.console;

public abstract class BasicCommand implements Command {
    private final String name;

    public BasicCommand(String name) {
        this.name = name;
    }

    @Override
    public String getFuncName() {
        return name;
    }

    @Override
    public abstract byte accept(String[] args);
}
