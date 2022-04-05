#version 430

layout(location = 0) in vec3 vertexPos;

uniform mat4 transform;
uniform vec4 color_in;

out vec4 color;

void main() {
    gl_Position = transform * vec4(vertexPos, 1);
    color = color_in;
}
