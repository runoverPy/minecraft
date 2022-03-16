#version 430

layout(location = 0) in vec3 vertex;

uniform mat4 fullTransform;
uniform vec4 frameColor;

out vec4 color;

void main() {
    gl_Position = fullTransform * vec4(vertex, 1);
    color = frameColor;
}
