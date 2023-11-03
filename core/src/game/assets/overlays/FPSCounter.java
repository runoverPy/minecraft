package game.assets.overlays;

import game.assets.mcui.Component;
import org.joml.Matrix4f;

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
}
