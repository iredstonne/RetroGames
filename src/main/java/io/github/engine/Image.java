package io.github.engine;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

public class Image {

    private final int width;
    private final int height;
    private final ByteBuffer pixels;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = BufferUtils.createByteBuffer((this.width * this.height * 3));
    }

    public int createTexture() {
        final int texturesId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texturesId);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, this.width, this.height, 0, GL_RGB, GL_BYTE, this.pixels.flip());
        return texturesId;
    }

}
