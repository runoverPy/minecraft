package game.main;

import org.joml.Vector3i;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        Vector3i a1, a2, c;
        a1 = new Vector3i(-4);
        a2 = new Vector3i(4, 4, 4);
        c = new Vector3i(-1);

        Comparator<Vector3i> comparator = (o1, o2) -> {
            int LT = -1, EQ = 0, GT = 1;
            double dist = new Vector3i(o1).sub(c).length() - new Vector3i(o2).sub(c).length();
            if (o1 == o2) return EQ;
            if (dist == 0d) return GT;
            return dist < 0d ? LT : GT;
        };

        System.out.println(comparator.compare(a1, a2));
    }
}
