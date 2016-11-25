package com.jukusoft.lwjgl.tutorial.glfw;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

/**
 * Some utilities for GLFW
 *
 * Created by Justin on 25.11.2016.
 */
public class GLFWUtils {

    /**
    * error callback listener
    */
    private static GLFWErrorCallback errorCallback = null;

    /**
     * initialize GLFW system
     *
     * @link https://github.com/lwjglgamedev/lwjglbook/blob/master/chapter01/src/main/java/org/lwjglb/game/Main.java
     */
    public static void init () {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW!");
        }
    }

    public static void shutdownGLFW () {
        // Terminate GLFW and release the GLFWerrorfun
        glfwTerminate();

        //release memory of error callback
        errorCallback.free();
    }


}
