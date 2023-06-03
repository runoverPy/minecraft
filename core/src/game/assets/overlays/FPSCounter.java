package game.assets.overlays;

import game.assets.mcui.Component;
import game.assets.ui_elements.asset.ColorBox;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class FPSCounter extends Component {
    private final Deque<Long> frames;
    private final int slots = 100;

    public FPSCounter() {
        this.frames = new LinkedList<>();
    }

    @Override
    public void render(Matrix4f matrix) {

    }

    public void addFrame(long time) {
        frames.addFirst(time);
    }

    public void clean() {
        double time = 0;
        double maxTime = 1e3;
        for (Iterator<Long> times = frames.iterator(); times.hasNext();) {
            double next = times.next();
            if (time > maxTime) times.remove();
            else time += next;
        }
    }

    public double getAvgFrameTime() {
        int frames = 0;
        long sum = 0;
        for (double value : this.frames) {
            frames++;
            sum += value;
        }
        return (double) sum / frames;
    }

    public double getAvgFrameRate() {
        return 1000 / getAvgFrameTime();
    }

    public void draw(int pxScale, Matrix4f matrix4f, int x, int y) { // todo
        int cols = 24;
        int colW = 1, colS = 1;

        ColorBox colorBox = new ColorBox(26, 64, x, y, null, new Vector4f(0.5f, 0.5f, 0.5f, 1f));
        colorBox.draw(pxScale, matrix4f);

        int i = 0;
        for (double value : frames) {
            new ColorBox(1, (int) Math.max(1000/value, 60), 1 + i, 1, null, new Vector4f()).draw(pxScale, matrix4f);
            i++;
        }
    }
}
