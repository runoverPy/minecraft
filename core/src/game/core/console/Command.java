package game.core.console;

public interface Command {
    String getFuncName();

    byte accept(String[] args);
}
