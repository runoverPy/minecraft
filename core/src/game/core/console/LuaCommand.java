package game.core.console;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaCommand implements Command {
    private final String name;
    // reference to shell environment
    private final String code;

    public LuaCommand(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String getFuncName() {
        return name;
    }

    @Override
    public byte accept(String[] args) {
        Globals runtime = JsePlatform.standardGlobals();
        LuaTable vyperTable = new LuaTable(), argsTable = new LuaTable();
        for (int i = 0; i < args.length; i++) {
            argsTable.set(i+1, LuaValue.valueOf(args[i]));
        }
        vyperTable.set("arg", argsTable);
        runtime.set("vyper", vyperTable);
        try {
            runtime.load(code).call();
        } catch (LuaError luaError) {
            System.out.println("\033[0;91m" + luaError + "\033[0m");
            return -128;
        }
        return 0;
    }
}
