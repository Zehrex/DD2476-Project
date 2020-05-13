2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Helper/ShaderHelper.java
package com.naive.phase.Auxiliary.Helper;

import com.naive.phase.Phase;
import com.naive.phase.PhaseEvent;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ShaderHelper {

    public static void useShader(int shader) {
        useShader(shader, null);
    }

    public static void setUniformf(String name, int shader, float v) {
        int i = ARBShaderObjects.glGetUniformLocationARB(shader, name);
        ARBShaderObjects.glUniform1fARB(i, v);
    }

    public static void setUniformi(String name, int shader, int v) {
        int i = ARBShaderObjects.glGetUniformLocationARB(shader, name);
        ARBShaderObjects.glUniform1iARB(i, v);
    }

    public static void useShader(int shader, Consumer<Integer> callback) {

        ARBShaderObjects.glUseProgramObjectARB(shader);

        if (shader != 0) {
            int time = ARBShaderObjects.glGetUniformLocationARB(shader, "time");
            ARBShaderObjects.glUniform1fARB(time, PhaseEvent.clientTick / 20.f);
            int width = ARBShaderObjects.glGetUniformLocationARB(shader, "width");
            ARBShaderObjects.glUniform1iARB(width, Minecraft.getMinecraft().displayWidth);
            int height = ARBShaderObjects.glGetUniformLocationARB(shader, "height");
            ARBShaderObjects.glUniform1iARB(height, Minecraft.getMinecraft().displayHeight);

            if (callback != null)
                callback.accept(shader);
        }
    }

    public static void releaseShader() {
        useShader(0, null);
    }

    public static int createProgram(String frag, String vert) {

        int vertId = 0, fragId = 0, program = 0;

        if (vert != null)
            vertId = createShader(StringHelper.getShader(vert), ARBVertexShader.GL_VERTEX_SHADER_ARB);
        if (frag != null)
            fragId = createShader(StringHelper.getShader(frag), ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);

        program = ARBShaderObjects.glCreateProgramObjectARB();
        if (program == 0)
            return 0;

        if (vert != null)
            ARBShaderObjects.glAttachObjectARB(program, vertId);
        if (frag != null)
            ARBShaderObjects.glAttachObjectARB(program, fragId);

        ARBShaderObjects.glLinkProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == 0) {
            Phase.logger.error(ARBShaderObjects.glGetInfoLogARB(program, ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)));
            return 0;
        }

        ARBShaderObjects.glValidateProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == 0) {
            return 0;
        }

        return program;
    }

    public static void deleteShader(int id) {
        if (id != 0)
            ARBShaderObjects.glDeleteObjectARB(id);
    }

    private static int createShader(String fn, int type) {
        int shader = 0;
        try {
            shader = ARBShaderObjects.glCreateShaderObjectARB(type);

            if (shader == 0)
                return 0;
            String fc = readlines(fn);
            ARBShaderObjects.glShaderSourceARB(shader, fc);
            ARBShaderObjects.glCompileShaderARB(shader);

            if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == 0)
                throw new RuntimeException();

            return shader;
        } catch (UnsupportedEncodingException e) {
            Phase.logger.error("Cannot resolve shader file encoding!");
            return -1;
        } catch (RuntimeException e) {
            Phase.logger.error(getLogInfo(shader));
            ARBShaderObjects.glDeleteObjectARB(shader);
            return -1;
        }
    }

    private static String readlines(String fn) throws UnsupportedEncodingException {
        InputStream in = ShaderHelper.class.getResourceAsStream(fn);
        return in == null ? "" : new BufferedReader(new InputStreamReader(in, "UTF-8")).lines().collect(Collectors.joining("\n"));
    }

    private static String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj,
                ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
}
