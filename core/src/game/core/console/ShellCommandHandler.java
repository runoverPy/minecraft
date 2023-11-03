package game.core.console;

import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class ShellCommandHandler extends CommandHandler<ShellCommandEnvironment> {
    public ShellCommandHandler(CommandLibrary library, ShellCommandEnvironment environment) {
        super(library, environment);
    }

    public ShellCommandHandler(ShellCommandEnvironment environment) {
        this(new CommandLibrary(), environment);
    }

    private static String[] parseCommand(String command) {
        try {
            StringBuilder builder = new StringBuilder();
            StringReader inputReader = new StringReader(command);
            ArrayList<String> tokens = new ArrayList<>();
            boolean escaped = false;
            int collectValue = 0;
            int collectRemain = 0;
            enum Quote {DOUBLE, SINGLE}
            Quote quote = null;
            while (inputReader.ready()) {
                int nextChar = inputReader.read();

                if (nextChar == -1) {
                    if (!builder.isEmpty()) {
                        tokens.add(builder.toString());
                        builder.setLength(0);
                    }
                    break;
                }

                if (escaped) {
                    escaped = false;
                    switch (nextChar) {
                        case '0' -> builder.append('\0');
                        case 'n' -> builder.append('\n');
                        case 'r' -> builder.append('\r');
                        case 't' -> builder.append('\t');
                        case 'f' -> builder.append('\f');
                        case 'b' -> builder.append('\b');
                        case 'x' -> collectRemain = 2;
                        case 'u' -> collectRemain = 4;
                        case 'U' -> collectRemain = -1;
                        default -> builder.append((char) nextChar);
                    }
                } else if (collectRemain > 0) {
                    collectValue = collectValue << 4 | (0xF & Character.digit(nextChar, 16));
                    if (--collectRemain == 0) {
                        String intermediate = String.valueOf(Character.toChars(collectValue % 0x10FFFF));
                        builder.append(intermediate);
                        collectValue = 0;
                    }
                } else if (collectRemain < 0) {
                    switch ((char) nextChar) {
                        case '{' -> {
                            if (collectRemain == -1) collectRemain = -2;
                            else
                                throw new IllegalStateException("Unicode escape sequences must contain only one open parenthesis");
                        }
                        case '}' -> {
                            if (collectRemain == -2) {
                                collectRemain = 0;
                                builder.append(Character.toChars(collectValue % 0x10FFFF));
                                collectValue = 0;
                            } else
                                throw new IllegalStateException("Closed braces in unicode escape sequences must be preceded by an open brace");
                        }
                        default -> {
                            if (collectRemain == -2) {
                                int digit = Character.digit(nextChar, 16);
                                if (digit == -1)
                                    throw new IllegalStateException("Unicode escape sequence may only contain characters in the range [0-9a-fA-F]");
                                collectValue = collectValue << 4 | (0xF & digit);
                            } else
                                throw new IllegalStateException("Unicode escape sequence must begin with open brace");
                        }
                    }
                } else switch (nextChar) {
                    case '\\' -> escaped = true;
                    case ' ' -> {
                        if (quote != null) {
                            builder.append(' ');
                        } else {
                            if (!builder.isEmpty()) {
                                tokens.add(builder.toString());
                                builder.setLength(0);
                            }
                        }
                    }
                    case '"' -> {
                        if (quote != Quote.SINGLE) {
                            if (quote == null) {
                                quote = Quote.DOUBLE;
                            } else {
                                quote = null;
                            }
                        } else builder.append('"');
                    }
                    case '\'' -> {
                        if (quote != Quote.DOUBLE) {
                            if (quote == null) {
                                quote = Quote.SINGLE;
                                collectRemain = 4;
                            } else {
                                quote = null;
                            }
                        } else builder.append('\'');
                    }
                    // special characters
                    default -> builder.append((char) nextChar);
                }
            }
            return tokens.toArray(String[]::new);
        } catch (IOException ioException) {
            throw new AssertionError(ioException);
        }
    }

    @Override
    public void execCommand(String command) {
        if (command.equals("")) return;
        String[] parsedCommand;
        try {
            parsedCommand = parseCommand(command);
        } catch (IllegalStateException illegalStateException) {
            System.out.println("\033[0;91m" + illegalStateException + "\033[0m");
            return;
        }
        String funcName = parsedCommand[0];
        String[] funcArgs = Arrays.copyOfRange(parsedCommand, 1, parsedCommand.length);
        getLibrary().executeCommand(funcName, funcArgs);
    }

    public static class LibCore extends CommandModule<ShellCommandEnvironment> {
        public LibCore(ShellCommandEnvironment environment) {
            super(environment);
        }

        @Override
        public void loadCommands(CommandLibrary library) {
            library.addFunctions(
              new BasicCommand("pwd") {
                  @Override
                  public byte accept(String[] args) {
                      System.out.println(getCmdEnv().getDirectory());
                      return 0;
                  }
              },
              new BasicCommand("cd") {
                  @Override
                  public byte accept(String[] args) {
                      if (args.length > 0) {
                          getCmdEnv().changeDirectory(args[0]);
                      } else {
                          getCmdEnv().changeDirectory(ShellCommandEnvironment.HOME);
                      }
                      return 0;
                  }
              },
              new ArgparseCommand("mkdir") {
                  @Override
                  protected void initParser(ArgumentParser parser) {
                      parser.defaultHelp(true);
                      parser.addArgument("dirname")
                        .required(true)
                        .help("creates a new directory");
                  }

                  @Override
                  public byte accept(Namespace namespace) {
                      System.out.println("creating directory " + getCmdEnv().getDirectory().resolve((String) namespace.get("dirname")));
                      return 0;
                  }
              },
              new BasicCommand("login") {
                  @Override
                  public byte accept(String[] args) {
                      if (args.length > 0) {
                          getCmdEnv().setUserName(args[0]);
                          return 0;
                      } else {
                          return -128;
                      }
                  }
              },
              new ArgparseCommand("usermod") {
                  @Override
                  protected void initParser(ArgumentParser parser) {
                      parser.addArgument("username").type(String.class).help("username of user to modify");
                      parser.addArgument("--group, -g").dest("group").help("set primary group to GROUP");
                      parser.addArgument("--set-groups, -G").dest("groups").help("set secondary groups to GROUPS");
                      parser.addArgument("--add-groups, -A").dest("groups").help("add GROUPS to secondary groups");
                      parser.addArgument("--set-name", "-n").dest("new_name").help("set user's username to NEW_NAME");
                  }

                  @Override
                  public byte accept(Namespace namespace) {
                      System.out.println(namespace);
                      return 0;
                  }
              },
              new BasicCommand("sethost") {
                  @Override
                  public byte accept(String[] args) {
                      if (args.length > 0) {
                          getCmdEnv().setHostName(args[0]);
                          return 0;
                      } else {
                          return -128;
                      }
                  }
              },
              new BasicCommand("jshell") {
                  @Override
                  public byte accept(String[] args) {
                      return 0;
                  }
              },
              new BasicCommand("lua") {
                  Globals globals = JsePlatform.standardGlobals();

                  @Override
                  public byte accept(String[] args) {
                      if (args.length > 0) {
                          try {
                              globals.load(args[0]).call();
                          } catch (LuaError luaErr) {
                              System.out.println("\033[0;91m" + luaErr + "\033[0m");
                          }
                          return 0;
                      } else {
                          System.out.println("\033[0;91m" + "insufficient arguments" + "\033[0m");
                          return -128;
                      }
                  }
              },
              new ArgparseCommand("define") {
                  @Override
                  protected void initParser(ArgumentParser parser) {
                      Subparser luaParser = parser.addSubparsers().dest("lang").addParser("lua");
                      luaParser.addArgument("name");
                      luaParser.addArgument("definition");
                  }

                  @Override
                  public byte accept(Namespace namespace) {
                      if (namespace.get("lang") == "lua") {
                          library.addFunction(new LuaCommand(namespace.get("name"), namespace.get("definition")));
                      }
                      return 0;
                  }
              },
              new BasicCommand("passwd") {
                  @Override
                  public byte accept(String[] args) {
                      System.out.print("Enter Password: ");
                      Scanner scanner = new Scanner(System.in);
                      StringBuilder builder = new StringBuilder();
                      while (scanner.hasNext(".")) {
                          String next = scanner.next(".");
                          if (next == null || Objects.equals(next, "\n")) break;
                          builder.append(next);
                          System.out.println("\b*");
                      }
                      System.out.println(builder);
                      return 0;
                  }
              }
            );
        }
    }

    public static void main(String[] args) {
        CommandLibrary commandLibrary = new CommandLibrary();
        ShellCommandEnvironment environment = new ShellCommandEnvironment();
        new LibCore(environment).loadCommands(commandLibrary);
        ShellCommandHandler commandHandler = new ShellCommandHandler(commandLibrary, environment);

        commandHandler.execCommand("echo \"siculut vyper interactive console V0.1.dev1\"");
        Scanner scanner = new Scanner(System.in);
        while (!environment.isExit()) {
            System.out.print(environment.getUserName() + "@" + environment.getHostName() + ":" + environment.getDirectory() + "$ ");
            if (scanner.hasNextLine())
                commandHandler.execCommand(scanner.nextLine());
            else environment.exit();
        }
        System.exit(environment.getExitCode());
    }
}
