package game.assets.mcui.data;

public interface Observable<CL extends ChangeListener<?>> {
    void addListener(CL listener);

    void removeListener(CL listener);
}
