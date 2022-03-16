#version 430

in vec4 color;

layout(location = 0) out vec4 diffuseColor;

void main() {
    diffuseColor = color;
}
