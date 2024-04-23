package io.github.engine.shader;

import static org.lwjgl.opengl.GL30.*;

public enum ShaderType {

    VERTEX, FRAGMENT;

    public int getAddress() {
        return switch (this) {
            case VERTEX -> GL_VERTEX_SHADER;
            case FRAGMENT -> GL_FRAGMENT_SHADER;
        };
    }

}
