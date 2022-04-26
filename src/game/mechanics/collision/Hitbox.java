package game.mechanics.collision;

import game.core.server.Server;
import game.util.Util;
import org.javatuples.Pair;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.EnumMap;
import java.util.Map;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Hitbox {
    private static final float[] vertices = new float[] {
            -0.5f, -0.5f, 0,
            -0.5f, -0.5f, 1,
            -0.5f, 0.5f, 0,
            -0.5f, 0.5f, 1,
            0.5f, -0.5f, 0,
            0.5f, -0.5f, 1,
            0.5f, 0.5f, 0,
            0.5f, 0.5f, 1,
    };
    private static final int[] cubeIndices = new int[]{
            0, 1,
            0, 2,
            0, 4,
            1, 3,
            1, 5,
            2, 3,
            2, 6,
            4, 5,
            4, 6,
            3, 7,
            5, 7,
            6, 7,
    };

    private static final int vertexBuffer, cubeIndexBuffer, programID, fullTransform, frameColor;

    static {
        vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        cubeIndexBuffer = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeIndexBuffer);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, cubeIndices, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        programID = Util.genProgram(
                new Pair<>("/shaders/cubeframe-vs.glsl", GL_VERTEX_SHADER),
                new Pair<>("/shaders/cubeframe-fs.glsl", GL_FRAGMENT_SHADER)
        );
        fullTransform = glGetUniformLocation(programID, "fullTransform");
        frameColor = glGetUniformLocation(programID, "frameColor");
    }

    private final float width, depth, height; // x, y, z respectively

    public Hitbox(float width, float depth, float height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

    public EnumMap<Direction, Boolean> worldCollisions(Vector3f pos, Server server) {
        EnumMap<Direction, Boolean> collisions = new EnumMap<>(Direction.class);
        return collisions;
    }

    public boolean collides(Vector3f pos, Vector3f point) {
        return  pos.x - width / 2 <= point.x && point.x <= pos.x + width / 2 &&
                pos.y - depth / 2 <= point.y && point.y <= pos.y + depth / 2 &&
                pos.z - height / 2 <= point.z && point.z <= pos.z + height / 2;
    }

    public boolean intersects(Vector3f pos1, Hitbox box2, Vector3f pos2) {
        return false;
    }

    public void draw(Matrix4f matrixPV, Vector3f pos) {
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeIndexBuffer);

        glUseProgram(programID);
        glUniformMatrix4fv(fullTransform, false, matrixPV
                .translate(pos, new Matrix4f())
                .scale(width, depth, height)
                .get(new float[16])
        );
        glUniform4fv(frameColor, new float[] {0, 0, 1, 1});

        glDrawElements(GL_LINES, 24, GL_UNSIGNED_INT, NULL);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * returns an array of the corners of the hitbox.
     * @param hitboxPos the current position of the hitbox, located at the center of the base of the box.
     * @return An array containing the absolute vectors for the corners of the hitbox
     */
    public Vector3f[] getCorners(Vector3f hitboxPos) {
        Vector3f[] out = new Vector3f[8];
        for (int i = 0; i < 8; i++) {
            out[i] = new Vector3f(
                    width * (-0.5f + i % 2),
                    depth * (-0.5f + (float) (i / 2) % 2),
                    height * (float) (i / 4)
            ).add(hitboxPos);
        }
        return out;
    }
}
