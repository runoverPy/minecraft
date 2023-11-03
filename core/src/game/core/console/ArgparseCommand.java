package game.core.console;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.helper.HelpScreenException;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public abstract class ArgparseCommand implements Command {
    private final String name;
    private final ArgumentParser parser;

    public ArgparseCommand(String name) {
        this.name = name;
        this.parser = ArgumentParsers.newFor(name).build();
        initParser(parser);
    }

    @Override
    public final String getFuncName() {
        return name;
    }

    @Override
    public final byte accept(String[] args) {
        try {
            return accept(parser.parseArgs(args));
        } catch (HelpScreenException e) {
            return 0;
        } catch (ArgumentParserException ape) {
            System.out.println("\033[0;91m" + ape + "\033[0m");
            return -128;
        }
    }

    protected abstract void initParser(ArgumentParser parser);

    public abstract byte accept(Namespace namespace);
}
