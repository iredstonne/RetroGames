package io.github.engine.renderer;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public final class RenderSystem {

    public static void clear(int mask) {
        glClear(mask);
    }

    public static void clearColor(int red, int green, int blue, int alpha) {
        glClearColor(red, green, blue, alpha);
    }

    public static int genTextures() {
        return glGenTextures();
    }

    public static void bindTexture(int target, int id) {
        glBindTexture(target, id);
    }

    public static void texParameteri(int target, int key, int value) {
        glTexParameteri(target, key, value);
    }

    public static void texImage2D(int target, int format, int type, int width, int height, ByteBuffer buffer) {
        glTexImage2D(target, 0, format, width, height, 0, format, type, buffer);
    }

}
