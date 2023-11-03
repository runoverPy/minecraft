package game.core.console;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandLibrary {
    protected final Map<String, Command> functions = new HashMap<>();

    public void addFunction(Command func) {
        functions.put(func.getFuncName(), func);
    }

    public void addFunctions(Command... funcs) {
        for (Command func : funcs) {
            addFunction(func);
        }
    }

    public CommandLibrary() {
        addFunctions(
          new BasicCommand("echo") {
              @Override
              public byte accept(String[] args) {
                  System.out.println(String.join(" ", args));
                  return 0;
              }
          },
          new BasicCommand("parse") {
              @Override
              public byte accept(String[] args) {
                  System.out.println(Arrays.toString(args));
                  return 0;
              }
          },
          new BasicCommand("lscmd") {
              @Override
              public byte accept(String[] args) {
                  for (String cmdName : functions.keySet().stream().sorted().toList())
                      System.out.println(cmdName);
                  return 0;
              }
          },
          new BasicCommand("exit") {
              @Override
              public byte accept(String[] args) {
                  if (args.length > 0) {
                      int exitCode = Integer.parseInt(args[0]);
                      System.exit(exitCode);
                  } else {
                      System.exit(0);
                  }
                  return 0;
              }
          }
        );
    }

    public void executeCommand(String funcName, String[] args) {
        if (!functions.containsKey(funcName)) {
            System.out.println("\033[0;91m" + funcName + ": command not found" + "\033[0m");
            return;
        }
        Command func = functions.get(funcName);
        func.accept(args);
    }
}
