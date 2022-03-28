package game.core;


import game.util.Util;
import org.javatuples.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public final class ShaderManager {
    private final Map<String, Shader> shaders;
    private Shader active;

    public ShaderManager() {
        this.shaders = new HashMap<>();
        this.active = null;
    }

    public ShaderManager(String json) {
        this();
    }

    private static int genShader(String shaderPath, int shaderType) {
        try {
            char[] buffer = new char[1024];
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("Loading shader file: " + shaderPath);
            InputStream stream = Util.class.getResourceAsStream(shaderPath);
            assert stream != null;
            Reader in = new InputStreamReader(stream, StandardCharsets.UTF_8);
            for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0;) {
                stringBuilder.append(buffer, 0, numRead);
            }
            String shaderData = stringBuilder.toString();

            int shaderID = glCreateShader(shaderType);
            glShaderSource(shaderID, shaderData);
            glCompileShader(shaderID);
            if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
                String err = glGetShaderInfoLog(shaderID);
                throw new RuntimeException("GLSL Compile Error in file " + shaderPath + "\n" + err);
            }
            return shaderID;
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
            return 0;
        }
    }

    @SafeVarargs
    private static int genProgram(Pair<String, Integer>... programShaders) {
        int[] shaders = new int[programShaders.length];
        int programID = glCreateProgram();

        for (int i = 0; i < programShaders.length; i++) {
            shaders[i] = genShader(programShaders[i].getValue0(), programShaders[i].getValue1());
            glAttachShader(programID, shaders[i]);
        }
        glLinkProgram(programID);
        if(glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException(glGetProgramInfoLog(programID));
        }
        for (int shaderID: shaders) {
            glDetachShader(programID, shaderID);
        }
        return programID;
    }

    @SafeVarargs
    public final boolean compile(String shaderName, Pair<String, Integer>... shaderSource) {
        if (!this.shaders.containsKey(shaderName)) {
            this.shaders.put(shaderName, new Shader(new String[]{}, shaderSource));
            return true;
        } else return false;
    }

    public Shader getShader(String shaderName) {
        return this.shaders.get(shaderName);
    }

    public void bindShader(String shaderName) {
        getShader(shaderName).bind();
    }

    public void freeShader() {
        glUseProgram(0);
    }

    public int shaderUniform(String shader, String uniform) {
        if (shader != null) return getShader(shader).getUniformPointer(uniform);
        else throw new NullPointerException("Currently no active shader");
    }

    public static class Shader {
        private final int shaderPointer;
        private final Map<String, Integer> shaderUniforms;

        @SafeVarargs
        public Shader(String[] uniformNames, Pair<String, Integer>... shaderSource) {
            this(shaderSource, uniformNames);
        }

        public Shader(Pair<String, Integer>[] shaderSource, String[] uniformNames) {
            this.shaderPointer = genProgram(shaderSource);
            this.shaderUniforms = new HashMap<>();
            for (String uniformName : uniformNames) {
                shaderUniforms.put(uniformName, glGetUniformLocation(shaderPointer, uniformName));
            }
        }

        public void bind() {
            glUseProgram(shaderPointer);
        }

        public int getUniformPointer(String uniform) {
            return shaderUniforms.get(uniform);
        }
    }
}
