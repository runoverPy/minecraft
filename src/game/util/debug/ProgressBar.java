package game.util.debug;

import java.util.Objects;

public class ProgressBar {
    private static final char[] chars = {(char)0x258f, (char)0x258e, (char)0x258d, (char)0x258c, (char)0x258b, (char)0x258a, (char)0x2589, (char)0x2588};


    private final String name;
    private final int finish;
    private int current;

    public ProgressBar(String name, int finish) {
        this.name = name;
        this.finish = finish;
        this.current = 0;
    }

    public void set(int i) {
        current = i;
        draw();
    }

    public void update(int i) {
        current += i;
        draw();
    }

    public void update() {
        update(1);
    }

    private void draw() {
        if (!Objects.equals(this.name, "")) {
            System.out.printf("%s: %3d%%|%-" + 100 + "s|\r", this.name, completionPercent(), bar());
        } else {
            System.out.printf("%3d%%|%-" + 100 + "s|\r", completionPercent(), bar());
        }
    }

    private int completionPercent() {
        return 100 * current / finish;
    }

    private String bar() {
        int length = 100;
        int currentValue = chars.length * length * current / finish;
//        System.out.println(chars.length + ", " + currentValue);
//        System.out.println(currentValue / chars.length + ", " + (currentValue % chars.length));
        return repeat(chars[7], currentValue / chars.length) + ((currentValue % chars.length > 0) ? chars[currentValue % chars.length] : "");
    }

    private String repeat(String s, int count) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < count; i++) {
            out.append(s);
        }
        return out.toString();
    }

    private String repeat(char c, int count) {
        return repeat(String.valueOf(c), count);
    }
}
