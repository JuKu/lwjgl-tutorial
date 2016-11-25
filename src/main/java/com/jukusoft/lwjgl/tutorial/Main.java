package com.jukusoft.lwjgl.tutorial;

import com.jukusoft.lwjgl.tutorial.glfw.GLFWUtils;
import com.jukusoft.lwjgl.tutorial.utils.OpenGLUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by Justin on 25.11.2016.
 */
public class Main {

    public static final int OPENGL_MAJOR_VERSION = 3;
    public static final int OPENGL_MINOR_VERSION = 2;

    public static void main (String[] args) {
        //configure logger log4j
        BasicConfigurator.configure();

        //initialize GLFW
        GLFWUtils.init();

        //configure GLFW window - optional, the current window hints are already the default
        glfwDefaultWindowHints();

        //hide window after creation
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);

        //set window resizeable
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        //use OpenGL 3.2 and above, dont use legacy OpenGL - use highest possible version of OpenGL
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, OPENGL_MAJOR_VERSION);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, OPENGL_MINOR_VERSION);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        //monitor id - dont set any monitor, so GLFW uses the first monitor id
        long monitor = NULL;

        //window size
        int width = 600;
        int height = 400;

        //window title
        String title = "My window title";

        //create an new GLFW window and save id of window, so we can change window settings later
        long windowID = glfwCreateWindow(width, height, title, monitor, NULL);

        //check, if error occours while creating window
        if (windowID == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        //get resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        //center window
        glfwSetWindowPos(
                windowID,
                (vidmode.width() - width) / 2,
                (vidmode.height() - height) / 2
        );

        //make the OpenGL context current
        glfwMakeContextCurrent(windowID);

        //make the window visible
        glfwShowWindow(windowID);

        /**
        *  from official documentation:
         *
         * "This line is critical for LWJGL's interoperation with GLFW's
         * OpenGL context, or any context that is managed externally.
         * LWJGL detects the context that is current in the current thread,
         * creates the ContextCapabilities instance and makes the OpenGL
         * bindings available for use."
        */
        GL.createCapabilities();

        // Set the clear color
        //glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        //print supported OpenGL version
        Logger.getRootLogger().info("supported OpenGL version: " + OpenGLUtils.getVersionString());

        //endless loop
        while (!Thread.currentThread().isInterrupted()) {
            glfwPollEvents();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //close window, set window should close flag to true
        glfwSetWindowShouldClose(windowID, true);

        //destroy window, release window and window callbacks
        glfwDestroyWindow(windowID);

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();
    }

}
