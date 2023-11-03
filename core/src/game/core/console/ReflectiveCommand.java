package game.core.console;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class ReflectiveCommand implements Command {
    private final String name;
    private final Method func;

    public ReflectiveCommand(String name, Method func) {
        Class<?> returnType = func.getReturnType();
        if (!Arrays.equals(func.getParameterTypes(), new Class[] { String[].class }) || (returnType != Integer.class && returnType != int.class))
            throw new InvalidParameterException("Method parameter list must only contain String[]");

        this.name = name;
        this.func = func;
    }

    @Override
    public String getFuncName() {
        return name;
    }

    @Override
    public byte accept(String[] args) {
        try {
            Object exitCode = func.invoke(null, new Object[] {args});
            return 0;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
