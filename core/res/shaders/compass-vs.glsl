#version 430

layout(location = 0) in vec3 vertex;
layout(location = 1) in vec3 color_in;

uniform mat4 fullTransform;

out vec4 color;

void main() {
    gl_Position = fullTransform * vec4(vertex, 1);
    color = vec4(color_in, 1);
}
