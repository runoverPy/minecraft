package game.core.console;

public class CommandEnvironment {
    private boolean exit;
    private int exitCode;

    public void exit() {
        exit(0);
    }

    public void exit(int code) {
        exit = true;
        exitCode = code;
    }

    public boolean isExit() {
        return exit;
    }

    public int getExitCode() {
        return exitCode;
    }
}
