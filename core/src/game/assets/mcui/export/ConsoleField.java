package game.assets.mcui.export;

import game.assets.mcui.Align;
import game.assets.mcui.Component;
import game.assets.mcui.atoms.ColorTile;
import game.assets.mcui.atoms.TextTile;
import game.assets.mcui.container.ScrollPane;
import org.joml.Vector4f;

public class ConsoleField extends Component {
    private ColorTile background;
    private ScrollPane scrollPane;
    private TextTile text;

    public ConsoleField() {
        background = new ColorTile(new Vector4f(0, 0, 0, 0.1f));
        scrollPane = new ScrollPane();
        scrollPane.setBarXActivity(ScrollPane.Activity.NEVER);
        scrollPane.setBarYActivity(ScrollPane.Activity.SOMETIMES);
        text = new TextTile();
        text.setAlign(Align.BOTTOM_LEFT);
        text.setOverflowBehavior(TextTile.OverflowBehavior.WRAP_INDENT);
        scrollPane.setContent(text);
    }
}
