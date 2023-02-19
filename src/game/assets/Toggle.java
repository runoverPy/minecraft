package game.assets;

public class Toggle {
    private final Callback callback;
    private boolean toggled;

    public Toggle(int key) {
        this.callback = new Callback(key, () -> {this.toggled = !this.toggled;});
    }

    public boolean isToggled() {
        return toggled;
    }

    public boolean check() {
        return this.callback.check();
    }
}
