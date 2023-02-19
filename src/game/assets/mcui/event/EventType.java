package game.assets.mcui.event;

import java.util.HashSet;
import java.util.Set;

public final class EventType<E extends Event> {
    public static final EventType<Event> ROOT = new EventType<>("ROOT");

    private Set<EventType<? extends E>> subtypes;

    private final EventType<? super E> supertype;
    private final String name;

    public EventType(EventType<? super E> supertype, String name) {
        if (supertype == null || name == null) {
            StringBuilder errMsgBuilder = new StringBuilder();
            errMsgBuilder.append("parameters ");
            if (supertype == null) errMsgBuilder.append("supertype");
            if (supertype == null && name == null) errMsgBuilder.append(" and ");
            if (name == null) errMsgBuilder.append("name");
            errMsgBuilder.append(" must not be null");
            throw new NullPointerException(errMsgBuilder.toString());
        }
        this.supertype = supertype;
        this.name = name;
        supertype.register(this);
    }

    private EventType(String rootName) {
        supertype = null;
        name = rootName;
    }

    private void register(EventType<? extends E> subtype) {
        if (subtypes == null) subtypes = new HashSet<>();
        for (EventType<? extends E> type : subtypes) {
            if (type.name.equals(subtype.name)) {
                throw new IllegalArgumentException("EventType \"" + subtype + "\""
                  + "with parent \"" + subtype.supertype + "\" already exists");
            }
        }
        subtypes.add(subtype);
    }

    public EventType<? super E>[] getTypeHierarchy() {
        return getTypeHierarchy(1);
    }

    private EventType<? super E>[] getTypeHierarchy(int length) {
        EventType<? super E>[] typeHierarchy;
        if (this == ROOT) {
            typeHierarchy = new EventType[length];
        } else {
            typeHierarchy = getSupertype().getTypeHierarchy(length + 1);
        }
        typeHierarchy[typeHierarchy.length - length] = this;
        return typeHierarchy;
    }

    public String getName() {
        return name;
    }

    public EventType<? super E> getSupertype() {
        return supertype;
    }

    @Override
    public String toString() {
        return name != null ? name : super.toString();
    }
}
