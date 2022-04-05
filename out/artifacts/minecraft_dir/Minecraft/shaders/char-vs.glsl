#version 430

layout(location = 0) in vec3 vertexPos;
layout(location = 1) in vec2 texturePos;

uniform mat4 transform;

out vec2 textureUV;

void main() {
    gl_Position = transform * vec4(vertexPos, 1);
    textureUV = texturePos;
}
