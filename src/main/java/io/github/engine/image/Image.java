package io.github.engine.image;

import java.nio.ByteBuffer;

import io.github.engine.util.Disposable;
import lombok.Getter;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;

@Getter
public class Image implements Disposable {

    private final int width;
    private final int height;
    private final ByteBuffer pixels;
    private int textureId;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = BufferUtils.createByteBuffer((this.width * this.height * 3));
        this.upload();
    }

    public void upload() {
        this.textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, (int) NULL, GL_RGB, this.width, this.height, (int) NULL, GL_RGB, GL_BYTE, this.pixels.flip());
    }

    @Override
    public void dispose() {
        if(this.textureId != NULL) {
            glDeleteTextures(this.textureId);
        }
    }

}
