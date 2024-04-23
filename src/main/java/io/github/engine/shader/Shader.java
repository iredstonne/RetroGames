package io.github.engine.shader;

import io.github.engine.util.Disposable;
import lombok.Getter;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Getter
public class Shader implements Disposable {

    private final String name;
    private final ShaderType type;
    private final String source;
    private int shaderId;

    public Shader(ShaderType type, String name, String source) {
        this.name = name;
        this.type = type;
        this.source = source;
    }

    public void compile() {
        this.shaderId = glCreateShader(this.type.getAddress());
        glShaderSource(this.shaderId, this.source);
        glCompileShader(this.shaderId);
        if(glGetShaderi(this.shaderId, GL_COMPILE_STATUS) != GL_TRUE) {
            final String message = glGetShaderInfoLog(this.shaderId);
            throw new IllegalStateException(String.format("Shader Compiling Error : %s", message));
        }
    }

    @Override
    public void dispose() {
        if(this.shaderId != NULL) {
            glDeleteShader(this.shaderId);
        }
    }

    public boolean isType(ShaderType type) {
        return this.type == type;
    }

}