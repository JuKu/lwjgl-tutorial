package com.jukusoft.lwjgl.tutorial.utils;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

/**
 * Utils for OpenGL 2.0+
 *
 * Created by Justin on 24.08.2016.
 */
public class OpenGLUtils {

    /**
    * get used OpenGL major version
     *
     * @return major version of used openGL
    */
    public static int getMajorVersion () {
        //get version as string, for example 3.2.0 - Build 20.19.15.4300
        String versionStr = OpenGLUtils.getVersionString();

        //split string
        String[] array = versionStr.split(".");

        //parse and return integer
        return Integer.parseInt(array[0]);
    }

    /**
     * get used OpenGL minor version
     *
     * @throws Exception if version string isnt valide
     *
     * @return minor version of used openGL
     */
    public static int getMinorVersion () {
        //get version as string, for example 3.2.0 - Build 20.19.15.4300
        String versionStr = OpenGLUtils.getVersionString();

        //split string
        String[] array = versionStr.split(".");

        //check if array has more than 1 elements
        if (array.length > 1) {
            throw new RuntimeException("Invalide OpenGL version string: " + versionStr + ".");
        }

        //parse and return integer
        return Integer.parseInt(array[1]);
    }

    /**
    * get used OpenGL version string
     *
     * @return used OpenGL version as string
    */
    public static String getVersionString () {
        //get and return OpenGL version
        return glGetString(GL_VERSION);
    }

}
