package game.assets.mcui;

import java.util.function.Supplier;

public class Button extends AbstractButton {
    private Runnable onClick;

    public Button(String desc, Runnable onClick, Supplier<Boolean> activeCondition) {
        super(desc, activeCondition);
        this.onClick = onClick;
    }

    public Button(String desc, Runnable onClick) {
        this(desc, onClick, TRUE);
    }
}
