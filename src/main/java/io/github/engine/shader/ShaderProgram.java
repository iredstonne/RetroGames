package io.github.engine.shader;

import io.github.engine.util.Disposable;
import lombok.Getter;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Getter
public class ShaderProgram implements Disposable {

    private final String name;
    private final Shader vShader;
    private final Shader fShader;
    private int programId;

    public ShaderProgram(String name, String vShaderSource, String fShaderSource) {
        this.name = name;
        this.vShader = new Shader(ShaderType.VERTEX, name, vShaderSource);
        this.fShader = new Shader(ShaderType.FRAGMENT, name, fShaderSource);
        this.validate();
    }

    private void validate() {
        if(this.vShader.isType(ShaderType.VERTEX)) {
            this.vShader.compile();
        }
        if(this.fShader.isType(ShaderType.FRAGMENT)) {
            this.fShader.compile();
        }
    }

    public void link() {
        this.programId = glCreateProgram();
        glAttachShader(this.programId, this.vShader.getShaderId());
        glAttachShader(this.programId, this.fShader.getShaderId());
        glLinkProgram(this.programId);
        if(glGetProgrami(this.programId, GL_LINK_STATUS) != GL_TRUE) {
            final String message = glGetProgramInfoLog(this.programId);
            throw new IllegalStateException(String.format("Shader Linking Error : %s", message));
        }
    }

    public void bind() {
        if(this.programId != NULL) {
            glUseProgram(this.programId);
        }
    }

    public void unbind() {
        if(this.programId != NULL) {
            glUseProgram((int) NULL);
        }
    }

    @Override
    public void dispose() {
        this.vShader.dispose();
        this.fShader.dispose();
        if(this.programId != NULL) {
            glDeleteShader(this.programId);
        }
    }

}
