#version 430

layout(points) in;
layout(line_strip, max_vertices=2) out;

in vec4 color_in;
out vec4 color_out;

void main() {
    color_out = color_in;
    vec4 position = gl_in[0].gl_Position;
    gl_Position = vec4(0);
    EmitVertex();
    gl_Position = position;
    EmitVertex();
    EndPrimitive();
}