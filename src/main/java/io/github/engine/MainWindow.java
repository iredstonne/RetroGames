package io.github.engine;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11C.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

public class MainWindow {

    private final String title;
    private final int defaultWidth, defaultHeight;
    private long handle;

    public MainWindow() {
        this.title = "MiniGames";
        this.defaultWidth = 1280;
        this.defaultHeight = 720;
        this.handle = NULL;
    }

    private void init() {
        if(!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW backend.");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        this.handle = glfwCreateWindow(this.defaultWidth, this.defaultHeight, this.title, NULL, NULL);
        if(this.handle == NULL) {
            throw new RuntimeException("Failed to create GLFW window.");
        }

        glfwMakeContextCurrent(this.handle);
        glfwSwapInterval(1);
        glfwShowWindow(this.handle);

        GL.createCapabilities();
    }

    private void loop() {
        while (!glfwWindowShouldClose(this.handle)) {
            glClearColor((255), (255), (255), (255));
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(this.handle);
            glfwPollEvents();
        }
    }

    private void dispose() {
        glfwDestroyWindow(this.handle);
        glfwTerminate();
    }

    public void run() {
        this.init();
        this.loop();
        this.dispose();
    }

}
