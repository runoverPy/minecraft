package game.assets.mcui.data;

import java.util.function.Consumer;

public interface ChangeListener<C extends ChangeListener.Change<?>> extends Consumer<C> {
    class Change<O> {
        private final O observable;

        public Change(O observable) {
            this.observable = observable;
        }

        public O getObservable() {
            return observable;
        }
    }
}
