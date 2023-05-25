package game.assets.event;

import java.util.*;

public final class EventType<E extends Event> {
    public static final EventType<Event> ROOT = new EventType<>("ROOT");

    private final Set<EventType<? extends E>> subtypes;

    private final EventType<? super E> supertype;
    private final String name;

    public EventType(EventType<? super E> supertype, String name) {
        if (supertype == null || name == null) {
            boolean st = supertype == null, nm = name == null;
            StringBuilder errMsgBuilder = new StringBuilder();
            errMsgBuilder.append("parameter");
            if (st && nm) errMsgBuilder.append("s");
            errMsgBuilder.append(" ");
            if (st) errMsgBuilder.append("supertype");
            if (st && nm) errMsgBuilder.append(" and ");
            if (nm) errMsgBuilder.append("name");
            errMsgBuilder.append(" must not be null");
            throw new NullPointerException(errMsgBuilder.toString());
        }
        this.supertype = supertype;
        this.subtypes = new HashSet<>();
        this.name = name;
        supertype.register(this);
    }

    private EventType(String rootName) {
        supertype = null;
        subtypes = new HashSet<>();
        name = rootName;
    }

    private void register(EventType<? extends E> subtype) {
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
            typeHierarchy = getSuperType().getTypeHierarchy(length + 1);
        }
        typeHierarchy[typeHierarchy.length - length] = this;
        return typeHierarchy;
    }

    public String getName() {
        return name;
    }

    public EventType<? super E> getSuperType() {
        return supertype;
    }

    /**
     *
     * @param type the EventType this will be compared to
     * @return true if {@code type} is a subtype of {@code this}, or are equal to eachother
     */
    public boolean isSubType(EventType<?> type) {
        for (EventType<?> current = type; current != this; current = current.getSuperType()) {
            if (current == null) return false;
        }
        return true;
    }

    /**
     *
     * @param type the EventType this will be compared to
     * @return true if {@code type} is a supertype of {@code this}, or are equal to eachother
     */
    public boolean isSuperType(EventType<?> type) {
        return type.isSubType(this);
    }

    public Integer getSeparation(EventType<?> superType) {
        if (this == superType) return 0;
        int separation = 0;
        for (EventType<?> current = superType; current != this; current = current.getSuperType()) {
            if (current == null) return null; // event types are unrelated
            separation++;
        }
        return separation;
    }

    @Override
    public String toString() {
        return name;
    }

    public static String getRootTree() {
        return ROOT.getTypeTree();
    }

    public String getTypeTree() {
        StringBuilder builder = new StringBuilder();
        print(builder, "", "");
        return builder.toString();
    }

    private void print(StringBuilder builder, String prefix, String childrenPrefix) {
        builder.append(prefix);
        builder.append(name != null ? name : super.toString());
        builder.append('\n');
        for (Iterator<EventType<? extends E>> it = subtypes.iterator(); it.hasNext();) {
            EventType<? extends  E> next = it.next();
            if (it.hasNext()) {
                next.print(builder, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(builder, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
