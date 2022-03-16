#version 430

in vec2 textureUV;

uniform vec4 color;
uniform sampler2D textureSampler;

layout(location = 0) out vec4 diffuseColor;

void main() {
    diffuseColor = color * texture(textureSampler, textureUV);
}
