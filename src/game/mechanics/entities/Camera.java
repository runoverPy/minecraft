package game.mechanics.entities;

import org.joml.*;

public interface Camera {
    Matrix4f getProjectionView();
    void update();

    void reset();

    Vector3f getCamPos();
    Vector3f getCamDir();
    Vector3i getCurrentChunk();

    double getHorizontal();
    double getVertical();
}
