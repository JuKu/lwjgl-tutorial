package com.jukusoft.lwjgl.tutorial;

import com.jukusoft.lwjgl.tutorial.glfw.GLFWUtils;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by Justin on 25.11.2016.
 */
public class Main {

    public static void main (String[] args) {
        //configure logger log4j
        BasicConfigurator.configure();

        //initialize GLFW
        GLFWUtils.init();

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();
    }

}
