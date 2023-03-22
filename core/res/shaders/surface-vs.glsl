#version 460

layout(location = 0) in vec3 vertexPos;
layout(location = 1) in vec2 texturePos;
layout(location = 2) in vec3 blockOffset;

uniform mat4 fullTransform;

out vec2 texCoord;

void main() {
    gl_Position = fullTransform * vec4(vertexPos + blockOffset, 1);
    texCoord = texturePos;
}
