package game.core.console;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * handles commands matching the grammar
 * cmd -> '/' commandWord word*
 * cmd -> string
 * This behavior emulates minecraft's command line parser
 */
public class SimpleCommandHandler extends CommandHandler<SimpleCommandEnvironment> {
    private final CommandLibrary library;

    public SimpleCommandHandler(CommandLibrary library, SimpleCommandEnvironment environment) {
        super(library, environment);
        this.library = library;
    }

    public SimpleCommandHandler(SimpleCommandEnvironment environment) {
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
                        if (quote == null && !builder.isEmpty()) continue;
                        if (quote != Quote.SINGLE) {
                            if (quote == null) {
                                quote = Quote.DOUBLE;
                            } else {
                                quote = null;
                                tokens.add(builder.toString());
                                builder.setLength(0);
                            }
                        } else builder.append('"');
                    }
                    case '\'' -> {
                        if (quote == null && !builder.isEmpty()) continue;
                        if (quote != Quote.DOUBLE) {
                            if (quote == null) {
                                quote = Quote.SINGLE;
                                collectRemain = 4;
                            } else {
                                quote = null;
                                tokens.add(builder.toString());
                                builder.setLength(0);
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

    public void execCommand(String command) {
        if (command.equals("")) return;
        if (command.charAt(0) == '/') {
            command = command.substring(1);
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
            library.executeCommand(funcName, funcArgs);
        }
        else System.out.println("[server] " + command);
    }

    public static void main(String[] args) {
        CommandLibrary commandLibrary = new CommandLibrary();
        SimpleCommandEnvironment environment = new SimpleCommandEnvironment();
        SimpleCommandHandler commandHandler = new SimpleCommandHandler(commandLibrary, environment);

        Scanner scanner = new Scanner(System.in);
        while (!environment.isExit()) {
            System.out.print("> ");
            if (scanner.hasNextLine())
                commandHandler.execCommand(scanner.nextLine());
            else environment.exit();
        }
        System.exit(environment.getExitCode());
    }
}
