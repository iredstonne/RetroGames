package io.github.engine.image;

import java.nio.ByteBuffer;

import io.github.engine.renderer.RenderSystem;
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
        final int texturesId = RenderSystem.genTextures();
        RenderSystem.bindTexture(GL_TEXTURE_2D, texturesId);
        RenderSystem.texParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
        RenderSystem.texParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
        RenderSystem.texParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        RenderSystem.texParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        RenderSystem.texImage2D(GL_TEXTURE_2D, GL_RGB, GL_BYTE, this.width, this.height, this.pixels.flip());
        return texturesId;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
