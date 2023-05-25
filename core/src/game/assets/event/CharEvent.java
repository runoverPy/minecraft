package game.assets.event;

public class CharEvent extends Event {
    public static final EventType<CharEvent> CHARACTER =
            new EventType<>(ANY, "CHARACTER");

    private char character;

    public CharEvent(char character) {
        super(CHARACTER);
        this.character = character;
    }
}
